package com.reaier.engking.domain.word;

import com.reaier.engking.constants.WordProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class English implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //英语单词
    protected String word;

    //用于查找的HASH值
    private int hash;

    //英式发音
    String enMp3;
    String enPhonetic;

    //美式发音
    String amMp3;
    String amPhonetic;


    Date time;

    WordProcess status;
}
