package com.sisyphus.auth.core.properties;

import lombok.Data;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 27/02/2023 00:55
 */
@Data
public class GoogleProperties {


    /**
     * Application id.
     */
    private String appId;


    /**
     * Application apiKey.
     */
    private String apiKey;

    /**
     * Application clientId.
     */
    private String clientId;
    private String clientSecret = "qq";
}
