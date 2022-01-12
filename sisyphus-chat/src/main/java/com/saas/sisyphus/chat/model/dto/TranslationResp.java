package com.saas.sisyphus.chat.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zard
 * @date Created in 12/01/2022 15:57
 */
@Data
public class TranslationResp {

    private int code;
    private String lang;
    private List<String> text;
}
