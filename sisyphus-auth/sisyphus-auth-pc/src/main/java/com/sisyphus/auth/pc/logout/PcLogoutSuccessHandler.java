package com.sisyphus.auth.pc.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisyphus.auth.core.SecurityResult;
import com.sisyphus.auth.core.properties.SecurityProperties;
import com.sisyphus.common.base.wapper.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 19:07
 */
public class PcLogoutSuccessHandler implements LogoutSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    private SecurityProperties securityProperties;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 当退出成功的时候，如果配置了一个页面，则跳转到页面，
        // 没有配置页面则打印session
        String signOutUrl = securityProperties.getBrowser().getSignOutUrl();
        if (StringUtils.isBlank(signOutUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            SecurityResult result = SecurityResult.ok("退出成功");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        } else {
            response.sendRedirect(signOutUrl);
        }
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
