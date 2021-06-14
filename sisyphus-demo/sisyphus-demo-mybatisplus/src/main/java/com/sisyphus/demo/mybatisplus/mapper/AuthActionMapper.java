package com.sisyphus.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sisyphus.demo.mybatisplus.model.domain.AuthAction;
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
public interface AuthActionMapper extends BaseMapper<AuthAction>, com.baomidou.mybatisplus.core.mapper.Mapper<AuthAction> {

    List<AuthAction> getOwnUacActionListByUserId(@Param("userId") Long userId);
}
