package com.sisyphus.common.base.wapper;

import com.sisyphus.common.base.util.PageUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 02:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageResponse<T> extends ResponseDTO<T> {

    private static final long serialVersionUID = 666985064788933395L;

    private PageUtil pageUtil;


    /**
     * Instantiates a new Page wrapper.
     */
    PageResponse() {
        super();
    }


    /**
     * Instantiates a new Page wrapper.
     *
     * @param code    the code
     * @param message the message
     */
    public PageResponse(int code, String message) {
        super(code, message);
    }

    /**
     * Instantiates a new pageWrapper default code=200
     *
     * @param result   the result
     * @param pageUtil the page util
     */
    public PageResponse(T result, PageUtil pageUtil) {
        super();
        this.setResult(result);
        this.setPageUtil(pageUtil);
    }

    /**
     * Instantiates a new Page wrapper.
     *
     * @param code     the code
     * @param message  the message
     * @param result   the result
     * @param pageUtil the page util
     */
    PageResponse(int code, String message, T result, PageUtil pageUtil) {
        super(code, message, result);
        this.pageUtil = pageUtil;
    }

    /**
     * Sets the 分页数据 , 返回自身的引用.
     *
     * @param pageUtil the page util
     *
     * @return the page wrapper
     */
    public PageResponse<T> pageUtil(PageUtil pageUtil) {
        this.setPageUtil(pageUtil);
        return this;
    }

    /**
     * Sets the 结果数据 , 返回自身的引用.
     *
     * @param result the result
     *
     * @return the page wrapper
     */
    @Override
    public PageResponse<T> result(T result) {
        super.setResult(result);
        return this;
    }
}
