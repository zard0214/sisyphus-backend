package com.sisyphus.gateway.config;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 12:16
 */
@Component
public class SentinelFallbackHandler implements BlockRequestHandler {

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {
        return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                .contentType(MediaType.valueOf("application/json;charset=UTF-8"))
                .bodyValue("{\n" +
                        "\"code\": 200,\n" +
                        "\"message\": \"请求过于频繁, 请稍后再试\"\n" +
                        "}");
    }
}
