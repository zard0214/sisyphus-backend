package com.sisyphus.provider.uac.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhecheng.zhao
 * @date Created in 20/05/2021 04:23
 */
@RestController
@RequestMapping(value = "/uac", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiRestController {

    @PostMapping(value = "/a")
    public String success() {
        return "success";
    }
}
