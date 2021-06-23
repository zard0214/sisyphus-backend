package com.sisyphus.common.base.exception;

import com.sisyphus.common.base.enums.ErrorCodeEnum;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 01:46
 */
public class GatewayException extends BaseException {

    /**
     * 异常码
     */
    protected int code;

    private static final long serialVersionUID = 3160241586346324994L;

    public GatewayException() {
    }

    public GatewayException(Throwable cause) {
        super(cause);
    }

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayException(int code, String message) {
        super(message);
        this.code = code;
    }

    public GatewayException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
    }

    public GatewayException(ErrorCodeEnum codeEnum, Object... args) {
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
