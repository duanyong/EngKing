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
}
