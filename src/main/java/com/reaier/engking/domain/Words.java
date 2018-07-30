package com.reaier.engking.domain;

import com.reaier.engking.constants.Language;
import com.reaier.engking.constants.RecitalPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //来源主键
    Integer sourceId;

    //单词主键
    String words;

    //单词所属语言
    Language language;

    //是否在计划表中
    RecitalPlan plan;

    //创建时间
    Date creatAt;
}
