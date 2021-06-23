package com.sisyphus.provide.udc.mapper;

import com.sisyphus.common.support.mybatis.IMapper;
import com.sisyphus.provide.udc.model.domain.UdcExceptionLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhecheng.zhao
 * @date Created in 14/06/2021 20:33
 */
@Mapper
@Repository
public interface UdcExceptionLogMapper extends IMapper<UdcExceptionLog> {
}
