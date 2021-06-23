package com.sisyphus.common.support.base;

import com.sisyphus.common.support.util.ThreadLocalMap;
import com.sisyphus.common.base.constant.GlobalConstant;
import com.sisyphus.common.base.dto.LoginAuthDTO;
import com.sisyphus.common.base.enums.ErrorCodeEnum;
import com.sisyphus.common.base.exception.BizException;
import com.sisyphus.common.support.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 00:24
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Gets login auth dto.
     *
     * @return the login auth dto
     */
    protected LoginAuthDTO getLoginAuthDTO() {
        LoginAuthDTO loginAuthDto = (LoginAuthDTO) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        if (ObjectUtil.isEmpty(loginAuthDto)) {
            throw new BizException(ErrorCodeEnum.GL99990403);
        }
        return loginAuthDto;
    }
}
