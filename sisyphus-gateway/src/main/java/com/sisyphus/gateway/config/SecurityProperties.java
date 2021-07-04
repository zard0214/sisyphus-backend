package com.sisyphus.gateway.config;

import com.sisyphus.auth.core.properties.BrowserProperties;
import com.sisyphus.auth.core.properties.OAuth2Properties;
import com.sisyphus.auth.core.properties.SocialProperties;
import com.sisyphus.auth.core.properties.ValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:35
 */
@Data
@Component
@ConfigurationProperties(prefix = "sisyphus.security")
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityProperties {

    /** imooc.security.browser 路径下的配置会被映射到该配置类中 */
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private SocialProperties social = new SocialProperties();
    private OAuth2Properties oauth2 = new OAuth2Properties();
}
