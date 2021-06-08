package com.sisyphus.auth.browser.support;

import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 19:11
 */
@Data
public class SocialUserInfo {

    private String providerId;

    private String providerUserId;

    private String nickname;

    private String headimg;
}
