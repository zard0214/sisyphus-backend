package com.sisyhus.demo.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sisyhus.demo.dubbo.provider.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhecheng.zhao
 * @date Created in 14/06/2021 16:19
 */
@Slf4j
@Component("providerService")
@Service(interfaceClass = ProviderService.class)
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String hello() {
        return "hello";
    }
}
