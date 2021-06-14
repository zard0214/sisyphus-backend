package com.sisphus.common.support.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 05:48
 */
public interface IMapper<T> extends BaseMapper<T>, Mapper<T> {
}
