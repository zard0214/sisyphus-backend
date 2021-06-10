package com.sisyphus.auth.browser.config;

import com.sisyphus.auth.browser.logout.ILogoutSuccessHandler;
import com.sisyphus.auth.browser.session.IInvalidSessionStrategy;
import com.sisyphus.auth.browser.session.ISessionInformationExpiredStrategy;
import com.sisyphus.auth.core.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 19:03
 */
@Configuration
public class PcSecurityBeanConfig {

    @Resource
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new IInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new ISessionInformationExpiredStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        ILogoutSuccessHandler iLogoutSuccessHandler = new ILogoutSuccessHandler();
        iLogoutSuccessHandler.setSecurityProperties(securityProperties);
        return iLogoutSuccessHandler;
    }
}

