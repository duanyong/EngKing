package com.reaier.engking.domain.view;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Data
@Immutable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "source_words_view")
public class SourceWords implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, insertable = false)
    private String id;

    @Column(name = "source_id", updatable = false, insertable = false)
    private Integer sourceId;

    @Column(name = "word_id", updatable = false, insertable = false)
    private Integer wordId;

    @Column(name = "user_id",updatable = false, insertable = false)
    private Integer userId;

    @Column(name = "word",updatable = false, insertable = false)
    protected String word;

    //英式发音
    @Column(name = "en_mp3",updatable = false, insertable = false)
    String enMp3;

    @Column(name = "en_phonetic",updatable = false, insertable = false)
    String enPhonetic;

    //美式发音
    @Column(name = "am_mp3",updatable = false, insertable = false)
    String amMp3;

    @Column(name = "am_phonetic",updatable = false, insertable = false)
    String amPhonetic;
}
