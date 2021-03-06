package com.sisyphus.gateway.handler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 12:16
 */
@Component
public class GatewayFallbackHandler extends SentinelGatewayBlockExceptionHandler {

    private List<ViewResolver> viewResolvers;
    private List<HttpMessageWriter<?>> messageWriters;

    public GatewayFallbackHandler(List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
        super(viewResolvers,serverCodecConfigurer);
        this.viewResolvers = viewResolvers;
        this.messageWriters = serverCodecConfigurer.getWriters();
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        //定义错误码返回
        if(throwable instanceof ResponseStatusException){
            HttpStatus status = ((ResponseStatusException) throwable).getStatus();
            if(HttpStatus.SERVICE_UNAVAILABLE.equals(status)){
                return handleBlockedRequest(serverWebExchange, throwable).flatMap(response -> writeErrorResponse(response, serverWebExchange));
            }
        }
        if(serverWebExchange.getResponse().isCommitted()){
            return Mono.error(throwable);
        }
        if(!BlockException.isBlockException(throwable)){
            return Mono.error(throwable);
        }
        return handleBlockedRequest(serverWebExchange, throwable).flatMap(response -> writeResponse(response, serverWebExchange));
    }


    private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable throwable) {
        return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, throwable);
    }

    private Mono<Void> writeResponse(ServerResponse response, ServerWebExchange exchange) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        serverHttpResponse.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        String data = "{\n" +
                "\"code\": 429,\n" +
                "\"message\": \"请求过于频繁, 请稍后再试\"\n" +
                "}";
        byte[] datas = data.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(datas);
        return serverHttpResponse.writeWith(Mono.just(buffer));
    }

    private Mono<Void> writeErrorResponse(ServerResponse response, ServerWebExchange exchange) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        serverHttpResponse.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        String data = "{\n" +
                "\"code\": 503,\n" +
                "\"message\": \"微服务故障, 请稍后再试\"\n" +
                "}";
        byte[] datas = data.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(datas);
        return serverHttpResponse.writeWith(Mono.just(buffer));
    }

}
