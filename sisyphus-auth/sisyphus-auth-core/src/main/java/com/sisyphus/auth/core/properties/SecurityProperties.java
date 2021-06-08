package com.sisyphus.auth.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:35
 */
@Data
@ConfigurationProperties(prefix = "sisyphus.security")
public class SecurityProperties {

    /** imooc.security.browser 路径下的配置会被映射到该配置类中 */
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private SocialProperties social = new SocialProperties();
    private OAuth2Properties oauth2 = new OAuth2Properties();
}
