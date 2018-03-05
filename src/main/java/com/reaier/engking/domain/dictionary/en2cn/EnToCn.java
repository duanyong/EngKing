package com.reaier.engking.domain.dictionary.en2cn;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class EnToCn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //英语单词主键
    @Column(name = "english_id", nullable = false, updatable = false, length = 32)
    Integer EnglishId;

    //词性
    @Column(name = "part", nullable = false, updatable = false, length = 32)
//    PartOfSpeech part;
    String part;

    //单词解释，相同词性之间用|分隔
    @Column(name = "means", nullable = false, updatable = false, columnDefinition = "TEXT")
    String means;
}
