package com.sisyphus.demo.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                MybatisAutoConfiguration.class})
@EnableDiscoveryClient
@MapperScan(basePackages = {
        "com.sisyphus.demo.mybatisplus.mapper",
})
public class SisyphusDemoMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusDemoMybatisplusApplication.class, args);
    }

}
