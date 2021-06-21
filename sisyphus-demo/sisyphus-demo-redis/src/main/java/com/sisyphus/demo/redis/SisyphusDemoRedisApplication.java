package com.sisyphus.demo.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"com.sisyphus.demo.redis"})
@EnableDiscoveryClient
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class SisyphusDemoRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusDemoRedisApplication.class, args);
    }

}
