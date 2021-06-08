package com.sisyphus.auth.core.properties;

import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:34
 */
@Data
public class BrowserProperties {

    /** 登录页面路径 */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginType loginType = LoginType.JSON;
    private int rememberMeSeconds = 60; // 记住我功能默认超时时间60秒
    /** 注册页面 */
    private String signUpUrl = "/sisyphus-signUp.html";
    /** 退出成功页面 */
    private String signOutUrl;

    private SessionProperties session = new SessionProperties();
}
