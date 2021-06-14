package com.sisyphus.auth.authorize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.sisyphus.auth.authorize.mapper.AuthUserMapper;
import com.sisyphus.auth.authorize.model.domain.AuthAction;
import com.sisyphus.auth.authorize.model.domain.AuthUser;
import com.sisyphus.auth.authorize.model.dto.AuthUserDTO;
import com.sisyphus.auth.authorize.model.dto.LoginRespDTO;
import com.sisyphus.auth.authorize.service.AuthActionService;
import com.sisyphus.auth.authorize.service.AuthUserService;
import com.sisyphus.auth.authorize.uitls.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 10:24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements AuthUserService {

    @Resource
    private AuthActionService authActionService;
    @Resource
    private AuthUserMapper authUserMapper;
    @Resource
    private TaskExecutor taskExecutor;

    @Override
    public AuthUserDTO findByPhone(String phone) {
        return authUserMapper.findByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AuthUserDTO findByLoginName(String loginName) {
        return authUserMapper.findByLoginName(loginName);
    }

    @Override
    public AuthUserDTO findUserInfoByUserId(Long userId) {
        return authUserMapper.findUserInfoByUserId(userId);
    }

    @Override
    public Collection<GrantedAuthority> loadUserAuthorities(Long userId) {
        List<AuthAction> ownAuthList = authActionService.getOwnActionListByUserId(userId);
        List<GrantedAuthority> authList = Lists.newArrayList();
        for (AuthAction action : ownAuthList) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(action.getActionCode());
            authList.add(grantedAuthority);
        }
        return authList;
    }

    @Override
    public LoginRespDTO loginResp(Long applicationId) {
        String loginName = SecurityUtils.getCurrentLoginName();
        if (StringUtils.isEmpty(loginName)) {
            log.error("操作超时, 请重新登录 loginName={}", loginName);
            Preconditions.checkArgument(StringUtils.isNotEmpty(loginName), "操作超时, 请重新登录");
        }
        AuthUserDTO authUserDTO = this.findByLoginName(loginName);
//        if (PublicUtil.isEmpty(authUserDTO)) {
//            log.info("找不到用户信息 loginName={}", loginName);
//            throw new BizException(ErrorCodeEnum.UAC10011002, loginName);
//        }
//        LoginAuthDTO loginAuthDTO = new LoginAuthDTO();
//        List<AuthMenuVO> authMenus = new LoginAuthDTO();
//        List<AuthRoleDTO> authRoles = new LoginAuthDTO();
//        LoginRespDTO loginRespDTO = new LoginRespDTO(loginAuthDTO, authMenus, authRoles);
//        return loginRespDTO;
        return new LoginRespDTO();
    }
}
