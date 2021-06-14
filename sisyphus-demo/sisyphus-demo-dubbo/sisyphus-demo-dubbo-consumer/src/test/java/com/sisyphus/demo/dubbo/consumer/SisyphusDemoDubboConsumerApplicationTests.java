package com.sisyphus.demo.dubbo.consumer;

import com.sisyphus.demo.dubbo.consumer.service.ConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SisyphusDemoDubboConsumerApplicationTests {

    @Resource
    private ConsumerService consumerService;

    @Test
    void contextLoads() {
        System.out.println(consumerService.consumer());
    }

}
