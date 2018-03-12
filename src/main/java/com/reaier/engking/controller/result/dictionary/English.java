package com.reaier.engking.controller.result.dictionary;

import com.google.gson.annotations.SerializedName;
import com.reaier.engking.controller.result.dictionary.en2cn.EnToCn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class English {
    @SerializedName("source_id")
    private Integer sourceId;

    @SerializedName("word_id")
    private Integer wordId;

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("word")
    protected String word;

    //英式发音
    @SerializedName("en_mp3")
    String enMp3;

    @SerializedName("en_phonetic")
    String enPhonetic;

    //美式发音
    @SerializedName("am_mp3")
    String amMp3;

    @SerializedName("am_phonetic")
    String amPhonetic;

    @SerializedName("means")
    List<EnToCn> means;
}
