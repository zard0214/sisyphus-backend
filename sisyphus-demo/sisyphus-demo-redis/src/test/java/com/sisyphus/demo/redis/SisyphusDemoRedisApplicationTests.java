package com.sisyphus.demo.redis;

import com.sisyphus.demo.redis.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SisyphusDemoRedisApplicationTests {

    @Resource
    private DataService dataService;
    @Test
    void contextLoads() {
    }

}
