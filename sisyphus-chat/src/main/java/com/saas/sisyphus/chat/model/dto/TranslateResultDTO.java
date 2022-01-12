package com.saas.sisyphus.chat.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author zard
 * @date Created in 09/01/2022 18:49
 */
@NoArgsConstructor
@Data
public class TranslateResultDTO {

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("query")
    private String query;

    @JsonProperty("translation")
    private String translation;

}
