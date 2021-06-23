package com.sisyphus.common.support.config;

//import com.sisyphus.common.support.interceptor.TokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author zhecheng.zhao
 * @date Created in 12/06/2021 11:44
 */
@Slf4j
@Configuration
public class CoreConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	@ConditionalOnMissingBean(HandlerInterceptor.class)
//	@ConditionalOnProperty(prefix = "sisphus.token.interceptor", name = "enable", havingValue = "false")
//	public TokenInterceptor tokenInterceptor() {
//		return new TokenInterceptor();
//	}
}
