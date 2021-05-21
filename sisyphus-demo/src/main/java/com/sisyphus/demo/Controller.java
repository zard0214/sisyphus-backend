package com.sisyphus.demo;

import com.sisyphus.common.base.wapper.WrapMapper;
import com.sisyphus.common.base.wapper.Wrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhecheng.zhao
 * @date Created in 20/05/2021 12:48
 */
@RestController
@RequestMapping(value = "/a")
public class Controller {

    @PostMapping(value = "/a")
    public Wrapper<Boolean> a() {
        return WrapMapper.ok();
    }
}
