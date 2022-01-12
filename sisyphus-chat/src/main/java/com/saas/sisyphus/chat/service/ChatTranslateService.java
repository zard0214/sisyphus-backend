package com.saas.sisyphus.chat.service;



/**
 * @author zard
 * @date Created in 09/01/2022 18:30
 */
public interface ChatTranslateService {

    String youdaoTranslate(String source, String from, String to);

    String yandexTranslate(String source, String from, String to);

    String translate(String source);

    String getLanguages(String ui);

    String detect(String text, String langFrom, String langTo);
}
