package com.sisyphus.auth.authorize.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 09:10
 */
public class AuthLoginAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final String CHAR_SET = "UTF-8";
    private static final String HTTP_METHOD_POST = "POST";

    public AuthLoginAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HTTP_METHOD_POST));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String body = StreamUtils.copyToString(httpServletRequest.getInputStream(), Charset.forName(CHAR_SET));
        String username;
        String password;
        if (!httpServletRequest.getMethod().equals(HTTP_METHOD_POST)) {
            throw new AuthenticationServiceException("Authentication method not supported: "
                    + httpServletRequest.getMethod());
        } else {
            JSONObject jsonObj = JSON.parseObject(body);
            username = jsonObj.getString("username");
            password = jsonObj.getString("password");
            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }
}

