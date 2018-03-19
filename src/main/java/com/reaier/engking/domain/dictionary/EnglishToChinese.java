package com.reaier.engking.domain.dictionary;

import com.google.gson.annotations.Expose;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class EnglishToChinese extends EnglishDictionary {
    private static final long serialVersionUID = 1L;
    @Id
    @Expose(serialize = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //英语单词主键
    @Expose(serialize = false)
    @Column(name = "english_id", nullable = false, updatable = false, length = 32)
    Integer englishId;

    //词性
    @Expose
    @Column(name = "part", nullable = false, updatable = false, length = 32)
    String part;

    //单词解释，相同词性之间用|分隔
    @Expose
    @Column(name = "means", nullable = false, updatable = false, columnDefinition = "TEXT")
    String means;
}
