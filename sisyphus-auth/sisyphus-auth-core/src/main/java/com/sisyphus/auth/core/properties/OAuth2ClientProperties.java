package com.sisyphus.auth.core.properties;

import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:34
 */
@Data
public class OAuth2ClientProperties {

    private String[] authorizedGrantTypes = {};
    private String[] redirectUris = {}; // 信任的回调域
    private String[] scopes = {};
    private int accessTokenValiditySeconds; // token有效期

    /**
     * 第三方应用appId
     */
    private String clientId;
    /**
     * 第三方应用appSecret
     */
    private String clientSecret;
    /**
     * 针对此应用发出的token的有效时间
     */
    private int accessTokenValidateSeconds = 7200;

    private int refreshTokenValiditySeconds = 2592000;

    private String scope;
}
