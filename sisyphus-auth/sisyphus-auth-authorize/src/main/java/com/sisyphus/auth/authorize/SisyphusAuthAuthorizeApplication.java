package com.sisyphus.auth.authorize;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(
        scanBasePackages = {
                "com.sisyphus.auth.authorize",
                "com.sisyphus.auth.core",
                "com.sisyphus.common.support",
                "com.sisyphus.auth.pc",
                "com.sisyphus.auth.app"
        },
        exclude = {DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                MybatisAutoConfiguration.class})
@EnableDiscoveryClient
@MapperScan(basePackages = {
        "com.sisyphus.auth.authorize.mapper",
})
@EnableWebSecurity
@EnableDubboConfiguration
public class SisyphusAuthAuthorizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusAuthAuthorizeApplication.class, args);
    }

}
