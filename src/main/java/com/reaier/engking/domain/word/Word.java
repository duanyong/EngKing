package com.reaier.engking.domain.word;

import lombok.*;
import lombok.experimental.NonFinal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Value
@NonFinal
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    //英语单词
    protected String word;

    //音标
    protected String phonetic;

    //用于查找的HASH值
    private int hash;
}
