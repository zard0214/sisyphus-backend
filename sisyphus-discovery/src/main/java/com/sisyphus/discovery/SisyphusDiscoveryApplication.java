package com.sisyphus.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SisyphusDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusDiscoveryApplication.class, args);
    }

}
