package com.sisyphus.auth.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 07:54
 */
@Data
public class AuthGrantedAuthority implements GrantedAuthority {
    /**
     * 权限类型
     */
    private String method;

    /**
     * 菜单url
     */
    private String url;

    public AuthGrantedAuthority(String url, String method) {
        this.url = url;
        this.method = method;
    }

    @Override
    public String getAuthority() {
        return this.url + ";" + this.method;
    }
}
