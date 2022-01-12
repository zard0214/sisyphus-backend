package com.saas.sisyphus.chat.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zard
 * @date Created in 09/01/2022 18:49
 */
@NoArgsConstructor
@Data
public class TranslateResultDTO {

    @JsonProperty("orientation")
    private String orientation;
    @JsonProperty("lanFrom")
    private String lanFrom;
    @JsonProperty("textAngle")
    private String textAngle;
    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("lanTo")
    private String lanTo;
    @JsonProperty("resRegions")
    private List<ResRegionsDTO> resRegions;

    @NoArgsConstructor
    @Data
    public static class ResRegionsDTO {
        @JsonProperty("boundingBox")
        private String boundingBox;
        @JsonProperty("linesCount")
        private Integer linesCount;
        @JsonProperty("lineheight")
        private Integer lineheight;
        @JsonProperty("context")
        private String context;
        @JsonProperty("linespace")
        private Integer linespace;
        @JsonProperty("tranContent")
        private String tranContent;
    }
}
