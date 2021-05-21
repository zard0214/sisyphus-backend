package com.sisyphus.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhecheng.zhao
 * @date Created in 20/05/2021 18:53
 */
@Component
@Slf4j
public class SecurityGatewayFilterFactory extends AbstractGatewayFilterFactory<SecurityGatewayFilterFactory.Config> {

    public SecurityGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return null;
    }


    public class Config {
    }
}