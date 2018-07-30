package com.reaier.engking.domain.dictionary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EnglishDictionary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @JsonProperty("english_id")
    @Column(name = "english_id",                columnDefinition = "INT UNSIGNED NOT NULL COMMENT '英语主键'")
    Integer englishId;
}
