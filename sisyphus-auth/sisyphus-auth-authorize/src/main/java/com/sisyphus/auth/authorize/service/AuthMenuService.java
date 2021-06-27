package com.sisyphus.auth.authorize.service;

import com.sisyphus.auth.authorize.model.domain.AuthMenu;
import com.sisyphus.auth.authorize.model.dto.AuthMenuDTO;
import com.sisyphus.auth.authorize.model.dto.AuthRoleDTO;
import com.sisyphus.auth.authorize.model.vo.AuthMenuVO;
import com.sisyphus.common.support.service.IService;

import java.util.List;
import java.util.Set;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 10:22
 */
public interface AuthMenuService extends IService<AuthMenu> {

    List<AuthMenuVO> findMenuTreeByRoles(Long userId, List<AuthRoleDTO> authRoles);

    List<AuthMenu> getMenuList(final Set<Long> menuIdList);

    List<AuthMenu> selectMenuList(AuthMenu authMenu);
}
