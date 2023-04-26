package com.reaier.engking.dictionary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.PartOfSpeech;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public class Translation {
    @ApiModelProperty(notes = "词性")
    @Enumerated(EnumType.STRING)
    @JsonProperty("part")
    PartOfSpeech part;

    @ApiModelProperty(notes = "翻译之后的文字")
    @Enumerated(EnumType.STRING)
    @JsonProperty("content")
    String content;
}
