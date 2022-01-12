package com.saas.sisyphus.chat;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.sisyphus.common.support",
                "com.saas.sisyphus.chat.web",
                "com.saas.sisyphus.chat.service",
                "com.saas.sisyphus.chat.model",
                "com.saas.sisyphus.chat.util",
        })
//@EnableDiscoveryClient
public class SisyphusChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusChatApplication.class, args);
    }

}
