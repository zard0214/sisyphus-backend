package coms.sisphus.common.support.base;

import com.sisyphus.common.base.constant.GlobalConstant;
import com.sisyphus.common.base.dto.LoginAuthDTO;
import com.sisyphus.common.base.enums.ErrorCode;
import com.sisyphus.common.base.exception.BizException;
import coms.sisphus.common.support.util.ObjectUtil;
import coms.sisphus.common.support.util.ThreadLocalMap;
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
            throw new BizException(ErrorCode.UC1001001);
        }
        return loginAuthDto;
    }

}
