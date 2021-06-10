package com.sisyphus.auth.app.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 17:57
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        /**
         * @see SpringSocialConfig#imoocSocialSecurityConfig()
         */
        if (beanName.equals("socialSecurityConfig")) {
            SpringSocialConfigurer config = (SpringSocialConfigurer) bean;
            config.signupUrl("/social/signUp");
            return bean;
        }
        return bean;
    }
}
