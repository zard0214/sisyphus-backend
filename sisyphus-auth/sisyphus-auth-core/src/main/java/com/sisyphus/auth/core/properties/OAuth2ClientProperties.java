package com.sisyphus.auth.core.properties;

import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:34
 */
@Data
public class OAuth2ClientProperties {

    private String clientId;
    private String clientSecret;
    private String[] authorizedGrantTypes = {};
    private String[] redirectUris = {}; // 信任的回调域
    private String[] scopes = {};
    private int accessTokenValiditySeconds; // token有效期
}
