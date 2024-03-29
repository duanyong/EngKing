package com.reaier.engking.dictionary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.Dialect;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Phonics {
    @ApiModelProperty(notes = "单词方言")
    @Enumerated(EnumType.STRING)
    @JsonProperty("dialect")
    Dialect dialect;

    @ApiModelProperty(notes = "音标")
    @JsonProperty("phonic")
    String phonic;

    @ApiModelProperty(notes = "发音地址")
    @JsonProperty("sound")
    String sound;
}
