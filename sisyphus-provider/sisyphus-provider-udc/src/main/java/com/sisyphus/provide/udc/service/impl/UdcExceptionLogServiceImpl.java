package com.sisyphus.provide.udc.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisyphus.common.base.wapper.Response;
import com.sisyphus.common.base.wapper.ResponseDTO;
import com.sisyphus.provide.udc.mapper.UdcExceptionLogMapper;
import com.sisyphus.provide.udc.model.domain.UdcExceptionLog;
import com.sisyphus.provide.udc.service.UdcExceptionLogService;
import com.sisyphus.provider.udc.api.model.dto.GlobalExceptionLogDTO;
import com.sisyphus.provider.udc.api.service.UdcExceptionLogDubboApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhecheng.zhao
 * @date Created in 14/06/2021 20:32
 */
@Slf4j
@Service("udcExceptionLogService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = UdcExceptionLogDubboApi.class)
@Transactional(rollbackFor = Exception.class)
public class UdcExceptionLogServiceImpl extends ServiceImpl<UdcExceptionLogMapper, UdcExceptionLog>
        implements UdcExceptionLogService, UdcExceptionLogDubboApi {

    @Override
    public ResponseDTO saveAndSendExceptionLog(GlobalExceptionLogDTO exceptionLogDTO) {
        log.info("saveAndSendExceptionLog");
        return Response.success("saveAndSendExceptionLog");
    }
}
