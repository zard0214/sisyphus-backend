package com.sisyphus.auth.authorize;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.sisyphus.auth.authorize.service.AuthUserService;
import com.sisyphus.provider.udc.api.model.dto.GlobalExceptionLogDTO;
import com.sisyphus.provider.udc.api.service.UdcExceptionLogDubboApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class SisyphusAuthAuthorizeApplicationTests {

    @Resource
    private AuthUserService authUserService;

    @Reference
    private UdcExceptionLogDubboApi udcExceptionLogDubboApi;

    @Test
    void loadUserAuthorities() {
        log.info(JSON.toJSONString(authUserService.loadUserAuthorities(1L)));
    }

    @Test
    void saveAndSendExceptionLog() {
        log.info(JSON.toJSONString(udcExceptionLogDubboApi.saveAndSendExceptionLog(new GlobalExceptionLogDTO())));
    }

}
