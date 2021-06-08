package com.sisyphus.auth.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:43
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);

}
