package com.sisyphus.demo.mybatisplus.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhecheng.zhao
 * @date Created in 07/06/2021 17:40
 */
@Data
public class KvDTO<K, V> implements Serializable {

    private static final long serialVersionUID = -7712636075929650779L;

    /**
     * Instantiates a new Kv dto.
     */
    public KvDTO() {
    }

    /**
     * Instantiates a new Kv dto.
     *
     * @param key   the key
     * @param value the value
     */
    public KvDTO(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * key
     */
    private K key;
    /**
     * value
     */
    private V value;

}
