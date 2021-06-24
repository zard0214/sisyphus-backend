package com.sisyphus.auth.authorize.mapper;

import com.sisyphus.auth.authorize.model.domain.AuthMenu;
import com.sisyphus.auth.authorize.model.vo.AuthMenuVO;
import com.sisyphus.common.support.mybatis.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 06:46
 */
@Mapper
@Repository
public interface AuthMenuMapper extends IMapper<AuthMenu> {

    List<AuthMenuVO> findMenuVoListByUserId(Long userId);

    List<AuthMenu> listMenu(Set<Long> menuIdList);

    List<AuthMenu> selectMenuList(AuthMenu uacMenu);
}
