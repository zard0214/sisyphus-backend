package com.sisyphus.auth.authorize.mapper;

import com.sisyphus.auth.authorize.model.domain.AuthUser;
import com.sisyphus.auth.authorize.model.dto.AuthUserDTO;
import com.sisyphus.common.support.mybatis.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 06:46
 */
@Mapper
@Repository
public interface AuthUserMapper extends IMapper<AuthUser> {

    AuthUserDTO findByPhone(@Param("phone") String phone);

    AuthUserDTO findByLoginName(@Param("loginName") String loginName);

    AuthUserDTO findUserInfoByUserId(@Param("userId") Long userId);

}
