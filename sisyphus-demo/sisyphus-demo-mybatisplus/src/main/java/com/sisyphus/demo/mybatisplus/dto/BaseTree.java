package com.sisyphus.demo.mybatisplus.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 00:04
 */
@Data
public class BaseTree<E, ID> implements Serializable {

    /**
     * ID
     */
    private ID id;

    /**
     * 父ID
     */
    private ID pid;

    /**
     * 是否含有子节点
     */
    private boolean hasChild = false;

    /**
     * 子节点集合
     */
    private List<E> children;
}