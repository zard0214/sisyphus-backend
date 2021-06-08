package com.sisyphus.auth.browser.uitl;

import com.sisyphus.auth.core.social.SignUpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 18:58
 */
@Component
public class BrowserSignUpUtils implements SignUpUtils {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Override
    public void saveConnection(ServletWebRequest request, ConnectionData connectionData) {
        // 浏览器环境下不用处理
    }

    @Override
    public void doPostSignUp(String userId, ServletWebRequest request) {
        providerSignInUtils.doPostSignUp(userId, request);
    }
}