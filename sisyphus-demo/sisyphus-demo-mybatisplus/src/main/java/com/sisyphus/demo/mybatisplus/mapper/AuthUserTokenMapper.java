package com.sisyphus.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sisyphus.demo.mybatisplus.model.domain.AuthUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 06:46
 */
@Mapper
@Repository
public interface AuthUserTokenMapper extends BaseMapper<AuthUserToken>, com.baomidou.mybatisplus.core.mapper.Mapper<AuthUserToken> {

}
