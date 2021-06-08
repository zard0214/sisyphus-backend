package com.sisyphus.auth.core.properties;

import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:35
 */
@Data
public class QQProperties {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;
    private String providerId = "qq";
}
