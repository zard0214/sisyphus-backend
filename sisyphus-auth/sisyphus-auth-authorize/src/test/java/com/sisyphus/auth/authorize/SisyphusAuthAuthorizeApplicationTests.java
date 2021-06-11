package com.sisyphus.auth.authorize;

import com.alibaba.fastjson.JSON;
import com.sisyphus.auth.authorize.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class SisyphusAuthAuthorizeApplicationTests {

    @Resource
    private AuthUserService authUserService;

    @Test
    void loadUserAuthorities() {
        log.info(JSON.toJSONString(authUserService.loadUserAuthorities(1L)));
    }

}
