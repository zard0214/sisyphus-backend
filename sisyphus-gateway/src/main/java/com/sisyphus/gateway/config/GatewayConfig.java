package com.sisyphus.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhecheng.zhao
 * @date Created in 15/06/2021 09:51
 */
@Configuration
public class GatewayConfig {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfig(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelFallbackHandler(viewResolvers, serverCodecConfigurer);
    }

    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    @PostConstruct
    public void doInit() {
        initGatewayRules();
        initDegradeRules();
    }


    private void initGatewayRules() {
         Set<GatewayFlowRule> rules = new HashSet<>();
         rules.add(new GatewayFlowRule("sisyphus-authorize")
                .setCount(1)
                .setIntervalSec(1)
        );
        GatewayRuleManager.loadRules(rules);
    }

    private void initDegradeRules() {
        DegradeRule degradeRule = new DegradeRule("sisyphus-provider-api") // 资源名称
                    .setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO) // 异常比率模式(秒级)
                    .setCount(0.5) // 异常比率阈值(50%)
                    .setMinRequestAmount(10)
                    .setTimeWindow(10); // 熔断降级时间(10s)
        DegradeRuleManager.loadRules(Collections.singletonList(degradeRule));
    }

}
