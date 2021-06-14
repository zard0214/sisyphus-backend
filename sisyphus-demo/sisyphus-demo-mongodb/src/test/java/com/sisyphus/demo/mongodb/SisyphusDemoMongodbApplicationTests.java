package com.sisyphus.demo.mongodb;

import com.sisyphus.demo.mongodb.service.AuthUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SisyphusDemoMongodbApplicationTests {

    @Resource
    private AuthUserService authUserService;

    @Test
    void contextLoads() {
        authUserService.testTransaction();
    }

}
