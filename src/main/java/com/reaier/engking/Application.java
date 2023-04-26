package com.reaier.engking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@EnableRetry
@SpringBootApplication
public class Application {
    static ApplicationContext context;
    public static void main(String[] args) {
        context = SpringApplication.run(Application.class, args);
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
