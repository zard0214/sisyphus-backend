package com.sisyphus.common.base.wapper;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 02:01
 */
public class Response {

    /**
     * Instantiates a new wrap mapper.
     */
    private Response() {
    }

    /**
     * Wrap.
     *
     * @param <E>     the element type
     * @param code    the code
     * @param message the message
     * @param o       the o
     *
     * @return the wrapper
     */
    public static <E> ResponseDTO<E> wrap(int code, String message, E o) {
        return new ResponseDTO<>(code, message, o);
    }

    /**
     * Wrap.
     *
     * @param <E>     the element type
     * @param code    the code
     * @param message the message
     *
     * @return the wrapper
     */
    public static <E> ResponseDTO<E> wrap(int code, String message) {
        return wrap(code, message, null);
    }

    /**
     * Wrap.
     *
     * @param <E>  the element type
     * @param code the code
     *
     * @return the wrapper
     */
    public static <E> ResponseDTO<E> wrap(int code) {
        return wrap(code, null);
    }

    /**
     * Wrap.
     *
     * @param <E> the element type
     * @param e   the e
     *
     * @return the wrapper
     */
    public static <E> ResponseDTO<E> wrap(Exception e) {
        return new ResponseDTO<>(ResponseDTO.ERROR_CODE, e.getMessage());
    }

    /**
     * Un wrapper.
     *
     * @param <E>     the element type
     * @param wrapper the wrapper
     *
     * @return the e
     */
    public static <E> E unWrap(ResponseDTO<E> wrapper) {
        return wrapper.getResult();
    }

    /**
     * Wrap ERROR. code=100
     *
     * @param <E> the element type
     *
     * @return the wrapper
     */
    public static <E> ResponseDTO<E> illegalArgument() {
        return wrap(ResponseDTO.ILLEGAL_ARGUMENT_CODE_, ResponseDTO.ILLEGAL_ARGUMENT_MESSAGE);
    }

    /**
     * Wrap ERROR. code=500
     *
     * @param <E> the element type
     *
     * @return the wrapper
     */
    public static <E> ResponseDTO<E> fail() {
        return wrap(ResponseDTO.ERROR_CODE, ResponseDTO.ERROR_MESSAGE);
    }


    /**
     * Error wrapper.
     *
     * @param <E>     the type parameter
     * @param message the message
     *
     * @return the wrapper
     */
    public static <E> ResponseDTO<E> fail(String message) {
        return wrap(ResponseDTO.ERROR_CODE, StringUtils.isBlank(message) ? ResponseDTO.ERROR_MESSAGE : message);
    }

    /**
     * Wrap SUCCESS. code=200
     *
     * @param <E> the element type
     *
     * @return the wrapper
     */
    public static <E> ResponseDTO<E> success() {
        return new ResponseDTO<>();
    }

    /**
     * Ok wrapper.
     *
     * @param <E> the type parameter
     * @param o   the o
     *
     * @return the wrapper
     */
    public static <E> ResponseDTO<E> success(E o) {
        return new ResponseDTO<>(ResponseDTO.SUCCESS_CODE, ResponseDTO.SUCCESS_MESSAGE, o);
    }
}
