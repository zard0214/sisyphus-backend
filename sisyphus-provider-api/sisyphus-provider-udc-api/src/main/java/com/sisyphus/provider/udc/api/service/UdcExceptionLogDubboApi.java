package com.sisyphus.provider.udc.api.service;

import com.sisyphus.common.base.wapper.ResponseDTO;
import com.sisyphus.provider.udc.api.model.dto.GlobalExceptionLogDTO;

/**
 * @author zhecheng.zhao
 * @date Created in 14/06/2021 19:47
 */
public interface UdcExceptionLogDubboApi {

    /**
     * save exception and send message
     * @param exceptionLogDTO
     * @return
     */
    ResponseDTO saveAndSendExceptionLog(GlobalExceptionLogDTO exceptionLogDTO);
}
