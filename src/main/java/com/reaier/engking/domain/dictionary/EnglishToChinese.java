package com.reaier.engking.domain.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class EnglishToChinese extends EnglishDictionary {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //英语单词主键
    @Expose(serialize = false)
    @SerializedName("english_id")
    @Column(name = "english_id", nullable = false, updatable = false, length = 32)
    private Integer englishId;

    //词性
    @Column(name = "part", nullable = false, updatable = false, length = 32)
    private String part;

    //单词解释，相同词性之间用|分隔
    @SerializedName("chinese")
    @Column(name = "means", nullable = false, updatable = false, columnDefinition = "TEXT")
    private String means;
}
