package com.sisyphus.provider.uac.mapper;

import com.sisyphus.common.support.mybatis.IMapper;
import com.sisyphus.provider.uac.model.domain.UacUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 06:46
 */
@Mapper
@Repository
public interface UacUserMapper extends IMapper<UacUser> {

}
