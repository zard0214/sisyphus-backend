package com.sisyphus.auth.authorize.aspect;

import com.sisyphus.common.base.wapper.Response;
import com.sisyphus.common.base.wapper.ResponseDTO;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 09:19
 */
@Aspect
@Component
@ConditionalOnClass(name = {"org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler"})
public class ResponseBodyResultHandlerAspect {

    @SneakyThrows
    @Around(value = "execution(* org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler.handleResult(..)) && args(exchange, result)",
            argNames = "point,exchange,result")
    public Object handleResult(ProceedingJoinPoint point, ServerWebExchange exchange, HandlerResult result) {
        final Mono responseMono = ((Mono) result.getReturnValue()).map(responseValue ->
                responseValue instanceof ResponseDTO ? responseValue : Response.success(responseValue));
        return point.proceed(Arrays.asList(
                exchange,
                new HandlerResult(result.getHandler(), responseMono, result.getReturnTypeSource())
        ).toArray());
    }
}