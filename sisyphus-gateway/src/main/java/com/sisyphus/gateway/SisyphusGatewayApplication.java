package com.sisyphus.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
@EnableDiscoveryClient
public class SisyphusGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusGatewayApplication.class, args);
    }

}
