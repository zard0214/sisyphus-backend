package com.sisyphus.common.support.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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

}
