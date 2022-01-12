package com.saas.sisyphus.chat;

import com.saas.sisyphus.chat.service.ChatTranslateService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SisyphusChatApplicationTests {

    @Resource
    ChatTranslateService chatTranslateService;

    @Test
    void contextLoads() {
        System.out.println(chatTranslateService.translate("human trash"));
//        System.out.println(chatTranslateService.getLanguages("Вы там"));
//        System.out.println(chatTranslateService.detect("are you ok" +
//                "","zh","ru"));
    }

}
