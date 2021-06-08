package com.sisyphus.auth.core.social;

import org.springframework.social.connect.ConnectionData;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:43
 */
public interface SignUpUtils {

    void saveConnection(ServletWebRequest request, ConnectionData connectionData);

    void doPostSignUp(String userId, ServletWebRequest request);
}

