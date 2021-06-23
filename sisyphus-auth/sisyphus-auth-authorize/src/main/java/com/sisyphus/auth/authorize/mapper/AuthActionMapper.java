package com.sisyphus.auth.authorize.mapper;

import com.sisyphus.auth.authorize.model.domain.AuthAction;
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
public interface AuthActionMapper extends IMapper<AuthAction> {

    List<AuthAction> getOwnUacActionListByUserId(@Param("userId") Long userId);
}
