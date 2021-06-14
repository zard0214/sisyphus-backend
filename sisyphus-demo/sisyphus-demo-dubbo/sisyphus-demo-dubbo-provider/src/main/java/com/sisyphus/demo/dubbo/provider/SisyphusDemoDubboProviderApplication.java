package com.sisyphus.demo.dubbo.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubboConfiguration
@SpringBootApplication
public class SisyphusDemoDubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusDemoDubboProviderApplication.class, args);
    }

}
