package com.reaier.engking.domain.word;

import com.reaier.engking.constants.WordProcess;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
public class EnWord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //英语单词
    protected String word;

    //用于查找的HASH值
    private int hash;

    //音标
    protected String phonetic;

    Date time;

    WordProcess status;

    @Builder(toBuilder = true)
    public EnWord(String word, String phonetic, WordProcess status) {
        this.word       = word;
        this.phonetic   = phonetic;
        this.status     = status;
        this.time       = new Date();
    }
}
