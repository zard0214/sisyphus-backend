package com.sisyphus.demo.redis;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableMethodCache(basePackages="com.sisyphus.demo.redis.model")
@SpringBootApplication(scanBasePackages = {"com.sisyphus.demo.redis","com.alicp.jetcache.autoconfigure"})
@EnableCreateCacheAnnotation
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class SisyphusDemoRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusDemoRedisApplication.class, args);
    }

}
