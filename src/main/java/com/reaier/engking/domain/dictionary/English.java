package com.reaier.engking.domain.dictionary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.WordProcess;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Builder
public class English {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    @Column(name = "word",                      columnDefinition = "VARCHAR(256) NOT NULL COMMENT '英语单词'")
    protected String word;

    @JsonProperty("en_mp3")
    @Column(name = "en_mp3",                    columnDefinition = "VARCHAR(256) NOT NULL COMMENT '英式发音'")
    String enMp3;

    @JsonProperty("en_phonetic")
    @Column(name = "en_phonetic",               columnDefinition = "VARCHAR(256) NOT NULL COMMENT '英式拼写'")
    String enPhonetic;

    @JsonProperty("am_mp3")
    @Column(name = "am_mp3",                    columnDefinition = "VARCHAR(256) NOT NULL COMMENT '美式发音'")
    String amMp3;

    @JsonProperty("am_phonetic")
    @Column(name = "am_phonetic",               columnDefinition = "VARCHAR(256) NOT NULL COMMENT '美式拼写'")
    String amPhonetic;

    @Column(name = "status",                    columnDefinition = "VARCHAR(256) NOT NULL COMMENT '专利或著作权号'")
    WordProcess status;

    @Column(name = "create_at",                 columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间'", insertable = false, updatable = false)
    Date createAt;
}
