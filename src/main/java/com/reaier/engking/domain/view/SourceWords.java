package com.reaier.engking.domain.view;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reaier.engking.domain.dictionary.EnglishDictionary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Immutable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "source_words_view")
public class SourceWords {
    @Id
    @Column(name = "id", updatable = false, insertable = false)
    private String id;

    @Expose
    @SerializedName("source_id")
    @Column(name = "source_id", updatable = false, insertable = false)
    private Integer sourceId;

    @Expose
    @SerializedName("word_id")
    @Column(name = "word_id", updatable = false, insertable = false)
    private Integer wordId;

    @Expose
    @SerializedName("user_id")
    @Column(name = "user_id",updatable = false, insertable = false)
    private Integer userId;

    @Expose
    @Column(name = "word",updatable = false, insertable = false)
    protected String word;

    //英式发音
    @Expose
    @SerializedName("en_mp3")
    @Column(name = "en_mp3",updatable = false, insertable = false)
    String enMp3;

    @Expose
    @SerializedName("en_phonetic")
    @Column(name = "en_phonetic",updatable = false, insertable = false)
    String enPhonetic;

    //美式发音
    @Expose
    @SerializedName("am_mp3")
    @Column(name = "am_mp3",updatable = false, insertable = false)
    String amMp3;

    @Expose
    @SerializedName("am_phonetic")
    @Column(name = "am_phonetic",updatable = false, insertable = false)
    String amPhonetic;

    @Transient
    List<? extends EnglishDictionary> means;
}
