package com.sisyphus.auth.core.qq.connet;

import com.sisyphus.auth.core.qq.api.QQ;
import com.sisyphus.auth.core.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:44
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    public static final String authorizeUrl = "https://graph.qq.com/oauth2.0/authorize";
    public static final String accessTokenUrl = "https://graph.qq.com/oauth2.0/token";
    private String appId;

    public QQServiceProvider(String appId, String secret) {
        // OAuth2Operations 有一个默认实现类，可以使用这个默认实现类
        // oauth2的一个流程服务
        super(new QQAuth2Template(appId, secret, authorizeUrl, accessTokenUrl));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
