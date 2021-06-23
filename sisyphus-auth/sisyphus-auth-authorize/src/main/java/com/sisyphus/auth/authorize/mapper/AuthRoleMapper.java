package com.sisyphus.auth.authorize.mapper;

import com.sisyphus.auth.authorize.model.domain.AuthRole;
import com.sisyphus.auth.authorize.model.dto.AuthRoleDTO;
import com.sisyphus.common.support.mybatis.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 06:46
 */
@Mapper
@Repository
public interface AuthRoleMapper extends IMapper<AuthRole> {

    List<AuthRoleDTO> findByUserId(@Param("userId") Long userId);
}
