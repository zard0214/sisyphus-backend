package com.sisyphus.auth.authorize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisyphus.auth.authorize.mapper.AuthMenuMapper;
import com.sisyphus.auth.authorize.mapper.AuthRoleMapper;
import com.sisyphus.auth.authorize.model.domain.AuthMenu;
import com.sisyphus.auth.authorize.model.domain.AuthRole;
import com.sisyphus.auth.authorize.model.dto.AuthRoleDTO;
import com.sisyphus.auth.authorize.model.vo.AuthMenuVO;
import com.sisyphus.auth.authorize.service.AuthMenuService;
import com.sisyphus.auth.authorize.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 10/06/2021 18:44
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuMapper, AuthMenu> implements AuthMenuService {

    @Resource
    private AuthMenuMapper authMenuMapper;

    @Override
    public List<AuthMenuVO> findByRoles(List<AuthRoleDTO> authRoles) {
        return null;
    }
}
