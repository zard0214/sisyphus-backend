package com.sisyphus.common.base.wapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 02:01
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseDTO<T> implements Serializable {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 4893280118017319089L;

    /**
     * 成功码.
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 成功信息.
     */
    public static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 错误码.
     */
    public static final int ERROR_CODE = 500;

    /**
     * 错误信息.
     */
    public static final String ERROR_MESSAGE = "内部异常";

    /**
     * 错误码：参数非法
     */
    public static final int ILLEGAL_ARGUMENT_CODE_ = 100;

    /**
     * 错误信息：参数非法
     */
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";

    /**
     * 编号.
     */
    private int code;

    /**
     * 信息.
     */
    private String message;

    /**
     * 结果数据
     */
    private T result;

    /**
     * Instantiates a new wrapper. default code=200
     */
    public ResponseDTO() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param code    the code
     * @param message the message
     */
    public ResponseDTO(int code, String message) {
        this(code, message, null);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param code    the code
     * @param message the message
     * @param result  the result
     */
    public ResponseDTO(int code, String message, T result) {
        super();
        this.code(code).message(message).result(result);
    }

    /**
     * Sets the 编号 , 返回自身的引用.
     *
     * @param code the new 编号
     *
     * @return the wrapper
     */
    private ResponseDTO<T> code(int code) {
        this.setCode(code);
        return this;
    }

    /**
     * Sets the 信息 , 返回自身的引用.
     *
     * @param message the new 信息
     *
     * @return the wrapper
     */
    private ResponseDTO<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * Sets the 结果数据 , 返回自身的引用.
     *
     * @param result the new 结果数据
     *
     * @return the wrapper
     */
    public ResponseDTO<T> result(T result) {
        this.setResult(result);
        return this;
    }

    /**
     * 判断是否成功： 依据 Wrapper.SUCCESS_CODE == this.code
     *
     * @return code =200,true;否则 false.
     */
    @JsonIgnore
    public boolean success() {
        return ResponseDTO.SUCCESS_CODE == this.code;
    }

    /**
     * 判断是否成功： 依据 Wrapper.SUCCESS_CODE != this.code
     *
     * @return code !=200,true;否则 false.
     */
    @JsonIgnore
    public boolean error() {
        return !success();
    }

    public static ResponseDTO error(String message, Object data) {
        return new ResponseDTO(ERROR_CODE, StringUtils.isEmpty(message) ? ERROR_MESSAGE : message, data);
    }

}
