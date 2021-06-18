package com.sisyphus.demo.redis.web;

import com.sisyphus.demo.redis.service.DataService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 20/05/2021 04:23
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiRestController {

    @Resource
    private DataService dataService;

    @GetMapping(value = "/jetcache")
    public String userCache() {
        return dataService.userCache("1");
    }

    @GetMapping(value = "/")
    public String success() {
        return "success";
    }

}
