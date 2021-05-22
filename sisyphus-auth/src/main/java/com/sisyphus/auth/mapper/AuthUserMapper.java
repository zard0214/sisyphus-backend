package com.sisyphus.auth.mapper;

import com.sisyphus.auth.model.domain.AuthUser;
import com.sisyphus.auth.model.dto.AuthUserDTO;
import coms.sisphus.common.support.mybatis.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 06:46
 */
@Mapper
@Repository
public interface AuthUserMapper extends IMapper<AuthUser> {

    AuthUserDTO findByLoginName(String username);

    AuthUserDTO findUserInfoByUserId(Long userId);
}
