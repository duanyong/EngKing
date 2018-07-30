package com.reaier.engking.domain.dictionary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.PartOfSpeech;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class EnglishToChinese extends EnglishDictionary {
    private static final long serialVersionUID = 1L;

    @JsonProperty("part")
    @Column(name = "part",                      columnDefinition = "VARCHAR(256) NOT NULL COMMENT '词性'")
    private PartOfSpeech part;

    @JsonProperty("chinese")
    @Column(name = "mean",                      columnDefinition = "TEXT NOT NULL COMMENT '翻译'")
    private String mean;
}
