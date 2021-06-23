package com.sisyphus.auth.authorize.web;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 20:05
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Web - AuthTokenController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthTokenController {
}
