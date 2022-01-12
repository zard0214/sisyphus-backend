package com.saas.sisyphus.chat.model.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author zard
 * @date Created in 09/01/2022 19:04
 */
@Data
public class TranslateQuery {

    @JsonProperty("type")
    private Integer type;
    @JsonProperty("type")
    private String source;
}
