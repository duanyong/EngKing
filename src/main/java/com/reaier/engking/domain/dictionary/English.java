package com.reaier.engking.domain.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reaier.engking.constants.WordProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class English implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //英语单词
    @Expose
    protected String word;

    //用于查找的HASH值
    private int hash;

    //英式发音
    @Expose
    @SerializedName("en_mp3")
    String enMp3;

    @Expose
    @SerializedName("en_phonetic")
    String enPhonetic;

    //美式发音
    @Expose
    @SerializedName("am_mp3")
    String amMp3;

    @Expose
    @SerializedName("am_phonetic")
    String amPhonetic;

    Date time;

    WordProcess status;
}
