package com.reaier.engking.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;


@Slf4j
@Configuration
public class RestTemplateConfiguration {
    final static TimeUnit unit = TimeUnit.SECONDS;
    private final static int CONNECTION_TIME            = 5;
    private final static int READ_TIMEOUT               = 30;
    private final static int ALIVE_TIME                 = 120;
    private final static int POOL_MAX_TOTAL             = 50;
    private final static int POOL_EVICT_SECONDS         = 300;
    private final static int RETRY_TIMES                 = 3;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // 切换类库
        OkHttpClient okHttpClient = okHttp3Client();
        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory(okHttpClient));

        return restTemplate;
    }

    /**
     * okHttp3Client 客户端
     * {@code https://square.github.io/okhttp/}
     *
     * @return httpClient
     */
    public OkHttpClient okHttp3Client() {
        return new OkHttpClient().newBuilder()
                .connectionPool(pool())
                .connectTimeout(CONNECTION_TIME, unit)
                .readTimeout(READ_TIMEOUT, unit)
                .callTimeout(ALIVE_TIME, unit)
                .hostnameVerifier((s, sslSession) -> false)
                // 禁止重定向
                .followRedirects(false)
                .build();
    }

    /**
     * 连接池
     *
     * @return 连接池
     */
    public ConnectionPool pool() {
        return new ConnectionPool(POOL_MAX_TOTAL, POOL_EVICT_SECONDS, unit);
    }
}
