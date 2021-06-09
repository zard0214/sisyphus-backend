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
public class SpringSocialConfigurerPostProcessor  implements BeanPostProcessor {
    // 任何bean初始化回调之前
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    //任何bean初始化回调之后
    // 在这里把之前浏览器中配置的注册地址更改为app中的处理控制器
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
