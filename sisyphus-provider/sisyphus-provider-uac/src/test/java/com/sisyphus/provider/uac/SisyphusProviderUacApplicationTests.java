package com.sisyphus.provider.uac;

import com.sisyphus.provider.uac.util.WebFlux;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SisyphusProviderUacApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(WebFlux.get("http://127.0.0.1"));;
    }

}
