package com.sisyphus.auth.authorize.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhecheng.zhao
 * @date Created in 20/05/2021 04:23
 */
@RestController
@RequestMapping(value = "/auth")
public class ApiRestController {

    @GetMapping(value = "/")
    public String success() {
        return "success";
    }

}
