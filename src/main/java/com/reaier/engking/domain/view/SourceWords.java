package com.reaier.engking.domain.view;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.reaier.engking.domain.adatper.SourceWordsAdatper;
import com.reaier.engking.domain.dictionary.EnglishDictionary;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "source_words_view")
@JsonAdapter(SourceWordsAdatper.class)
public class SourceWords {
    @Id
//    @Column(name = "id", updatable = false, insertable = false)
//    private String id;

    @Column(name = "source_id", updatable = false, insertable = false)
    private Integer sourceId;

    @Column(name = "word_id", updatable = false, insertable = false)
    private Integer wordId;

    @Column(name = "user_id", updatable = false, insertable = false)
    private Integer userId;

    @Expose
    @Column(name = "word", updatable = false, insertable = false)
    protected String word;

    //英式发音
    @Expose
    @Column(name = "en_mp3", updatable = false, insertable = false)
    String enMp3;

    @Expose
    @Column(name = "en_phonetic", updatable = false, insertable = false)
    String enPhonetic;

    //美式发音
    @Expose
    @Column(name = "am_mp3", updatable = false, insertable = false)
    String amMp3;

    @Expose
    @Column(name = "am_phonetic", updatable = false, insertable = false)
    String amPhonetic;

    @Transient
    List<? extends EnglishDictionary> means;
}
