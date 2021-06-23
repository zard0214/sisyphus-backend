package com.sisyphus.auth.authorize.service;

import com.sisyphus.auth.authorize.model.domain.AuthRole;
import com.sisyphus.auth.authorize.model.dto.AuthRoleDTO;
import com.sisyphus.common.support.service.IService;

import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 10:22
 */
public interface AuthRoleService extends IService<AuthRole> {

    List<AuthRoleDTO> findByUserId(Long userId);
}
