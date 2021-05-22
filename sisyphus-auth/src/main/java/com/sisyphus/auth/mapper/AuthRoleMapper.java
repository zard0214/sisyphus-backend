package com.sisyphus.auth.mapper;

import com.sisyphus.auth.model.domain.AuthRole;
import com.sisyphus.auth.model.domain.AuthUserToken;
import coms.sisphus.common.support.mybatis.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 06:46
 */
@Mapper
@Repository
public interface AuthRoleMapper extends IMapper<AuthRole> {
}
