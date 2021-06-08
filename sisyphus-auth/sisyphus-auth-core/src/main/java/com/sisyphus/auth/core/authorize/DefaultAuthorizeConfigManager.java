package com.sisyphus.auth.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:33
 */
@Component
public class DefaultAuthorizeConfigManager implements AuthorizeConfigManager {
    @Autowired
    private List<AuthorizeConfigProvider> providers;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider provider : providers) {
            provider.config(config);
        }
        // 除了上面配置的，其他的都需要登录后才能访问
//        config.anyRequest().authenticated();
    }
}
