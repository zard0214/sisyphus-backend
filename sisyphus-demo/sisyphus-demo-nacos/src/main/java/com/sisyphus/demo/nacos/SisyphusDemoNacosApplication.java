package com.sisyphus.demo.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SisyphusDemoNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusDemoNacosApplication.class, args);
    }

}
