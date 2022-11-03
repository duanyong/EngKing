package com.reaier.engking.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.SocketException;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class RestTemplateConfiguration {
    private final static int INIT_CONNECTION_TIME       = 5 * 1000;
    private final static int CONNECTION_TIME            = 5 * 1000;
    private final static int READ_TIMEOUT               = 30 * 1000;
    private final static int POOL_MAX_TOTAL             = 4096;
    private final static int POOL_MAX_ROUTE_TOTAL       = 200;
    private final static int POOL_EVICT_SECONDS         = 30;
    private final static int POOL_INACTIVITY_SECONDS    = 2;    // 空连接隔N秒验证一次
    private final static int POOL_CHECK_EVICT_SECONDS   = 10;
    private final static int RETRY_TIMES                 = 3;

    private final static Object syncLock = new Object();

    private static CloseableHttpClient httpClient;
    private static ScheduledExecutorService monitorExecutor;

    @Bean
    public RestTemplate restTemplate() {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(getHttpClient());

        return new RestTemplate(requestFactory);
    }

    private CloseableHttpClient getHttpClient() {
        if (httpClient != null) {
            return httpClient;
        }

        synchronized(syncLock) {
            //注册访问协议相关的Socket工厂
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", SSLConnectionSocketFactory.getSocketFactory())
                    .build();

            //HttpConnectionFactory:配置写请求/解析响应处理器
            HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory(
                    DefaultHttpRequestWriterFactory.INSTANCE,
                    DefaultHttpResponseParserFactory.INSTANCE
            );

            //DNS解析器
            DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
            //创建连接池管理器
            PoolingHttpClientConnectionManager pccm = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connectionFactory, dnsResolver);
            //设置默认的socket参数
            pccm.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());
            pccm.setMaxTotal(POOL_MAX_TOTAL);
            pccm.setDefaultMaxPerRoute(POOL_MAX_ROUTE_TOTAL);
            pccm.setValidateAfterInactivity(POOL_INACTIVITY_SECONDS * 1000);

            //配置默认的请求参数
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(INIT_CONNECTION_TIME)            // 连接超时设置为2s。建立TCP连接的时间
                    .setSocketTimeout(READ_TIMEOUT)                     // 等待数据超时设置为5s。等待服务器返回数据的时间
                    .setConnectionRequestTimeout(INIT_CONNECTION_TIME)  // 从连接池获取连接的等待超时时间设置为2s
                    .build();

            HttpRequestRetryHandler retryHandler = (exception, executionCount, context) -> {
                // 重试1次,从1开始
                if (executionCount > RETRY_TIMES) {
                    return false;
                }

                // 有可能服务器已经断开连接，尝试重试一下
                if (exception instanceof NoHttpResponseException) {
                    return true;
                } else return exception instanceof SocketException;
            };

            // 禁用Cookie管理，相同的Session中，写入的Cookie失败
            httpClient = HttpClients.custom()
                    .setConnectionManagerShared(false)
                    .disableCookieManagement()
                    .useSystemProperties()
                    .setDefaultRequestConfig(requestConfig)
                    .setConnectionManager(pccm)
                    .setMaxConnTotal(pccm.getMaxTotal())                   // 最大连接数
                    .setMaxConnPerRoute(pccm.getDefaultMaxPerRoute())      // 单个路由最多的连接数
                    .setConnectionTimeToLive(CONNECTION_TIME + READ_TIMEOUT, TimeUnit.MILLISECONDS)
                    // https://blog.csdn.net/baijiacha3793/article/details/101594243
                    // https://git.intra.weibo.com/AutoSupport/fengchao/-/issues/89
                    .evictIdleConnections(POOL_EVICT_SECONDS, TimeUnit.SECONDS)
                    .setRetryHandler(retryHandler)
                    .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                    .build();

            /**
             *JVM停止或重启时，关闭连接池释放掉连接
             */
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                    }
                }
            });

            // 定时清过期的连接
            monitorExecutor = Executors.newScheduledThreadPool(1);
            monitorExecutor.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    //关闭异常连接
                    pccm.closeExpiredConnections();
                    //关闭超超过x秒的空闲的连接
                    pccm.closeIdleConnections(POOL_EVICT_SECONDS, TimeUnit.SECONDS);
                }
            }, POOL_CHECK_EVICT_SECONDS, POOL_CHECK_EVICT_SECONDS, TimeUnit.SECONDS);
        }

        return httpClient;
    }
}
