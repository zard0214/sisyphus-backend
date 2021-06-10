package com.sisyphus.auth.app.config;

import com.sisyphus.auth.app.jwt.IJwtTokenEnhancer;
import com.sisyphus.auth.core.IRedisTokenStore;
import com.sisyphus.auth.core.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 19:28
 */
public class TokenStoreConfig {

    @Configuration
    @ConditionalOnProperty(prefix = "sisyphus.security.oauth2", name = "tokenStore", havingValue = "redis")
    public static class RedisConfig {

        @Resource
        private RedisConnectionFactory redisConnectionFactory;

        /**
         * Redis token store token store.
         *
         * @return token store
         */
        @Bean
        public TokenStore redisTokenStore() {
            return new IRedisTokenStore(redisConnectionFactory);
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "sisyphus.security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenConfig {

        @Resource
        private SecurityProperties securityProperties;

        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());  // 设置密钥
            return converter;
        }

        @Bean
        @ConditionalOnBean(TokenEnhancer.class)
        public TokenEnhancer jwtTokenEnhancer() {
            return new IJwtTokenEnhancer();
        }
    }
}