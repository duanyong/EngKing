package com.reaier.engking.translate.domain.iciba;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WordDesc {
    //英式发音
    @JsonProperty("ph_en_mp3")
    String enMp3;

    @JsonProperty("ph_en")
    String enPhonetic;

    //美式发音
    @JsonProperty("ph_am_mp3")
    String amMp3;

    @JsonProperty("ph_am")
    String amPhonetic;

    //单词解释
    @JsonProperty("parts")
    WordMean[] means;
}
