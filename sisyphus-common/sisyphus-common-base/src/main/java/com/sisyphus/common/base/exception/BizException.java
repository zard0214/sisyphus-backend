package com.sisyphus.common.base.exception;

import com.sisyphus.common.base.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhecheng.zhao
 * @date Created in 17/05/2021 23:44
 */
@Slf4j
public class BizException extends BaseException {

    /**
     * 异常码
     */
    protected int code;

    private static final long serialVersionUID = 3160241586346324994L;

    public BizException() {
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
    }

    public BizException(ErrorCode codeEnum, Object... args) {
        super(String.format(codeEnum.msg(), args));
        this.code = codeEnum.code();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
