package com.sisyphus.auth.authorize.model;

import com.sisyphus.auth.authorize.model.dto.AuthActionDTO;
import com.sisyphus.auth.authorize.model.dto.AuthRoleDTO;
import com.sisyphus.auth.authorize.model.enums.StatusType;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 07:29
 */
@Data
public class SocialUser implements SocialUserDetails {

    private static final long serialVersionUID = 312263123123123L;

    private static final String ENABLE = StatusType.ENABLE.getName();

    private Collection<GrantedAuthority> authorities;

    private Long userId;

    private String userName;

    private String loginName;

    private String loginPwd;

    private String status;

    private Long tenantId;

    private Long groupId;

    private String groupName;

    private List<AuthRoleDTO> authRoleList;

    private List<AuthActionDTO> authActionList;

    public SocialUser(Long userId, String userName, String loginName, String loginPwd, Long tenantId, Long groupId, String groupName, List<AuthRoleDTO> authRoleList, List<AuthActionDTO> authActionList) {
        this.userId = userId;
        this.userName = userName;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.tenantId = tenantId;
        this.groupId = groupId;
        this.groupName = groupName;
        this.authRoleList = authRoleList;
        this.authActionList = authActionList;
    }

    public SocialUser(Collection<GrantedAuthority> authorities, Long userId, String userName, String loginName, String loginPwd, String status, Long tenantId, Long groupId, String groupName, List<AuthRoleDTO> authRoleList, List<AuthActionDTO> authActionList) {
        this.authorities = authorities;
        this.userId = userId;
        this.userName = userName;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.status = status;
        this.tenantId = tenantId;
        this.groupId = groupId;
        this.groupName = groupName;
        this.authRoleList = authRoleList;
        this.authActionList = authActionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        List<AuthActionDTO> permissions = this.getAuthActionList();
        if (permissions != null) {
            for (AuthActionDTO permission : permissions) {
                GrantedAuthority grantedAuthority = new AuthGrantedAuthority(permission.getUrl(), permission.getMethod());
                authorityList.add(grantedAuthority);
            }
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.getLoginPwd();
    }

    @Override
    public String getUsername() {
        return this.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return StringUtils.equals(this.status, ENABLE);
    }

    public String getUserId() {
        return this.getUsername();
    }
}
