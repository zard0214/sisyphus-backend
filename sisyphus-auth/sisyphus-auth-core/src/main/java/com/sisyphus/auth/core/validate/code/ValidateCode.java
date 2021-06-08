package com.sisyphus.auth.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:40
 */
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 5927976362845150980L;

    protected String code;
    /** 过期时间 */
    protected LocalDateTime expireTime;

    /**
     * @param code
     * @param expireIn 过期时间，单位秒
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return this.expireTime.isBefore(LocalDateTime.now());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
