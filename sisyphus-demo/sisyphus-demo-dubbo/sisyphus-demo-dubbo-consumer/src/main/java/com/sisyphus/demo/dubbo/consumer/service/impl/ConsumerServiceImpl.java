package com.sisyphus.demo.dubbo.consumer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sisyphus.demo.dubbo.consumer.service.ConsumerService;
import com.sisyphus.demo.dubbo.provider.api.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhecheng.zhao
 * @date Created in 14/06/2021 16:26
 */

@Slf4j
@Component("consumerService")
public class ConsumerServiceImpl implements ConsumerService {

    @Reference
    private ProviderService providerService;

    @Override
    public String consumer() {
        return providerService.hello();
    }
}
