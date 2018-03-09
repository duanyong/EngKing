package com.reaier.engking.controller.result.dictionary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.controller.result.dictionary.en2cn.EnToCn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class English {
    @JsonProperty("source_id")
    private Integer sourceId;

    @JsonProperty("word_id")
    private Integer wordId;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("word")
    protected String word;

    //英式发音
    @JsonProperty("en_mp3")
    String enMp3;

    @JsonProperty("en_phonetic")
    String enPhonetic;

    //美式发音
    @JsonProperty("am_mp3")
    String amMp3;

    @JsonProperty("am_phonetic")
    String amPhonetic;

    @JsonProperty("means")
    List<EnToCn> means;
}
