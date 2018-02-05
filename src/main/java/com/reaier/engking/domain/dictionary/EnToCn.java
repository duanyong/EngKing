package com.reaier.engking.domain.dictionary;

import com.reaier.engking.constants.PartOfSpeech;
import com.reaier.engking.constants.WordProcess;
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
    @Column(name = "en_id", nullable = false, updatable = false, length = 32)
    Integer enId;

    //词性
    @Column(name = "part_of_speech", nullable = false, updatable = false, length = 32)
    PartOfSpeech partOfSpeech;

    //单词解释
    @Column(name = "desc", nullable = false, updatable = false, length = 32)
    String desc;

    WordProcess status;
}
