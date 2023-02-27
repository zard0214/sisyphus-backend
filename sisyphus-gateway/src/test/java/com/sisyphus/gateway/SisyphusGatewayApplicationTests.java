package com.sisyphus.gateway;

import com.sisyphus.gateway.util.WebFlux;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SisyphusGatewayApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(WebFlux.get("http://127.0.0.1"));
    }

}
