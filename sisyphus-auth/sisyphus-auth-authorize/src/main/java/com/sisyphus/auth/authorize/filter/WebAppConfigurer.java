package com.sisyphus.auth.authorize.filter;

//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 10:09
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
//    @ConditionalOnMissingBean(HandlerInterceptor.class)
//	@ConditionalOnProperty(prefix = "sisphus.token.interceptor", name = "enable", havingValue = "true")
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor());
    }
}