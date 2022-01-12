package com.saas.sisyphus.chat.service;



/**
 * @author zard
 * @date Created in 09/01/2022 18:30
 */
public interface ChatTranslateService {

    String translate(String source, String from, String to);

    String translate(String source);
}
