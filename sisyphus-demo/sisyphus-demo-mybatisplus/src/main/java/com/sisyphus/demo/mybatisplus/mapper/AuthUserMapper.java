package com.sisyphus.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sisyphus.demo.mybatisplus.model.domain.AuthUser;
import com.sisyphus.demo.mybatisplus.model.dto.AuthUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 06:46
 */
@Mapper
@Repository
public interface AuthUserMapper extends BaseMapper<AuthUser>, com.baomidou.mybatisplus.core.mapper.Mapper<AuthUser> {

    AuthUserDTO findByPhone(@Param("phone") String phone);

    AuthUserDTO findByLoginName(@Param("loginName") String loginName);

    AuthUserDTO findUserInfoByUserId(@Param("userId") Long userId);

}
