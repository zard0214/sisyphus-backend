package com.sisyphus.auth.authorize.service.impl;

import com.sisyphus.auth.authorize.mapper.AuthUserMapper;
import com.sisyphus.auth.authorize.model.dto.AuthUserDTO;
import com.sisyphus.auth.authorize.service.AuthUserService;
import coms.sisphus.common.support.base.BaseService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 10:24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthUserServiceImpl extends BaseService implements AuthUserService {

    @Resource
    private AuthUserMapper authUserMapper;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AuthUserDTO findByLoginName(String username) {
        return authUserMapper.findByLoginName(username);
    }

    @Override
    public AuthUserDTO findUserInfoByUserId(Long userId) {
        return authUserMapper.findUserInfoByUserId(userId);
    }

    @Override
    public Collection<GrantedAuthority> loadUserAuthorities(Long userId) {
        return null;
    }

}
