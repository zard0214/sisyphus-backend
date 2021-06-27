package com.sisyphus.auth.authorize.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sisyphus.auth.authorize.mapper.AuthMenuMapper;
import com.sisyphus.auth.authorize.model.domain.AuthMenu;
import com.sisyphus.auth.authorize.model.dto.AuthRoleDTO;
import com.sisyphus.auth.authorize.model.enums.StatusType;
import com.sisyphus.auth.authorize.model.vo.AuthMenuVO;
import com.sisyphus.auth.authorize.service.AuthMenuService;
import com.sisyphus.auth.authorize.uitls.TreeUtil;
import com.sisyphus.common.base.constant.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public List<AuthMenuVO> findMenuTreeByRoles(Long userId, List<AuthRoleDTO> authRoles) {
        // 1.查询该用户下所有的菜单列表
        List<AuthMenuVO> menuVoList = Lists.newArrayList();
        List<AuthMenu> menuList;
        Set<AuthMenu> menuSet = Sets.newHashSet();
        // 如果是admin则返回所有的菜单
        if (GlobalConstant.Sys.SUPER_MANAGER_USER_ID.equals(userId)) {
            // 1.1 查询该用户下所有的菜单列表
            menuList = authMenuMapper.selectList(new QueryWrapper<AuthMenu>().
                    eq("status", StatusType.ENABLE.getCode()).
                    orderByAsc("level").
                    orderByAsc("number"));
        } else {
            // 1.2查询该用户下所有的菜单列表
            menuVoList = authMenuMapper.findMenuVoListByUserId(userId);
            if (CollectionUtils.isEmpty(menuVoList)) {
                return null;
            }
            Set<Long> ids = Sets.newHashSet();
            for (final AuthMenuVO menuVo : menuVoList) {
                ids.add(menuVo.getId());
            }
            List<AuthMenu> ownMenuList = this.getMenuList(ids);
            // 查出所有含有菜单的菜单信息
            AuthMenu uacMenu = new AuthMenu();
            uacMenu.setStatus(StatusType.ENABLE.getCode());
            List<AuthMenu> allMenuList = this.selectMenuList(uacMenu);
            Map<Long, AuthMenu> map = Maps.newHashMap();
            for (final AuthMenu menu : allMenuList) {
                map.put(menu.getId(), menu);
            }
            for (final AuthMenu menu : ownMenuList) {
                getPid(menuSet, menu, map);
            }
            menuList = new ArrayList(menuSet);
        }
        List<AuthMenuVO> list = getMenuVo(menuList);
        if (!CollectionUtils.isEmpty(list)) {
            list.addAll(menuVoList);
        }
        // 2.递归成树
        return TreeUtil.getChildMenuVos(list, 0L);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<AuthMenu> selectMenuList(AuthMenu uacMenu) {
        return authMenuMapper.selectMenuList(uacMenu);
    }

    @Override
    public List<AuthMenu> getMenuList(final Set<Long> menuIdList) {
        return authMenuMapper.listMenu(menuIdList);
    }

    private void getPid(Set<AuthMenu> menuSet, AuthMenu menu, Map<Long, AuthMenu> map) {
        AuthMenu parent = map.get(menu.getPid());
        if (parent != null && parent.getId() != 0L) {
            menuSet.add(parent);
            getPid(menuSet, parent, map);
        }
    }

    private List<AuthMenuVO> getMenuVo(List<AuthMenu> list) {
        List<AuthMenuVO> menuVoList = Lists.newArrayList();
        for (AuthMenu uacMenu : list) {
            AuthMenuVO menuVo = new AuthMenuVO();
            BeanUtils.copyProperties(uacMenu, menuVo);
            menuVo.setUrl(uacMenu.getUrl());
            menuVo.setMenuName(uacMenu.getMenuName());
            menuVoList.add(menuVo);
        }
        return menuVoList;
    }

}
