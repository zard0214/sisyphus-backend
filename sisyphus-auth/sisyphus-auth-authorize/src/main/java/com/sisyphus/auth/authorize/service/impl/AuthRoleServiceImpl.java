package com.sisyphus.auth.authorize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisyphus.auth.authorize.mapper.AuthActionMapper;
import com.sisyphus.auth.authorize.mapper.AuthRoleMapper;
import com.sisyphus.auth.authorize.model.domain.AuthAction;
import com.sisyphus.auth.authorize.model.domain.AuthRole;
import com.sisyphus.auth.authorize.model.dto.AuthRoleDTO;
import com.sisyphus.auth.authorize.service.AuthActionService;
import com.sisyphus.auth.authorize.service.AuthRoleService;
import com.sisyphus.common.base.constant.GlobalConstant;
import com.sisyphus.common.base.enums.ErrorCodeEnum;
import com.sisyphus.common.base.exception.BizException;
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
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole> implements AuthRoleService {

    @Resource
    private AuthRoleMapper authRoleMapper;

    @Override
    public List<AuthRoleDTO> findByUserId(Long id) {
        return null;
    }
}
