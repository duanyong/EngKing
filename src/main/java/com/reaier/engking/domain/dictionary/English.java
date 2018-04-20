package com.reaier.engking.domain.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reaier.engking.constants.WordProcess;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Builder
public class English {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    //英语单词
    protected String word;

    //用于查找的HASH值
    private int hash;

    //英式发音
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

    @Expose(serialize = false)
    Date time;

    @Expose(serialize = false)
    WordProcess status;

    @Transient
    List<? extends EnglishDictionary> means;
}
