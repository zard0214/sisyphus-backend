package com.sisyphus.provide.udc;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.sisyphus.common.support"
        },
        exclude = {DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                MybatisAutoConfiguration.class})
@EnableDiscoveryClient
@MapperScan(basePackages = {
        "com.sisyphus.provider.udc.mapper",
})
@EnableDubboConfiguration
public class SisyphusProviderUdcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusProviderUdcApplication.class, args);
    }

}
