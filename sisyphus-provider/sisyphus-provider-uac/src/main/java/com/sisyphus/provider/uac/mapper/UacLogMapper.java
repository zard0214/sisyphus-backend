package com.sisyphus.provider.uac.mapper;

import com.sisyphus.common.support.mybatis.IMapper;
import com.sisyphus.provider.uac.model.domain.UacLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 16:54
 */
@Mapper
@Repository
public interface UacLogMapper extends IMapper<UacLog> {
}
