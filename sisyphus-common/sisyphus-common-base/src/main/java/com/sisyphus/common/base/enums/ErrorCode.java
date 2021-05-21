package com.sisyphus.common.base.enums;

/**
 * @author zhecheng.zhao
 * @date Created in 17/05/2021 23:43
 */
public enum ErrorCode {

    GE100500(500, "未知异常"),

    UC1001001(1001001, "登录超时");

    private int code;
    private String msg;

    /**
     * Msg string.
     *
     * @return the string
     */
    public String msg() {
        return msg;
    }

    /**
     * Code int.
     *
     * @return the int
     */
    public int code() {
        return code;
    }

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Gets enum.
     *
     * @param code the code
     *
     * @return the enum
     */
    public static ErrorCode getEnum(int code) {
        for (ErrorCode ele : ErrorCode.values()) {
            if (ele.code() == code) {
                return ele;
            }
        }
        return null;
    }
}
