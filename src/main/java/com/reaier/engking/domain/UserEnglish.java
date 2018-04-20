package com.reaier.engking.domain;

import com.reaier.engking.constants.Language;
import com.reaier.engking.constants.RecitalPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEnglish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //用户主键
    Integer userId;

    //来源主键
    Integer sourceId;

    //单词主键
    Integer englishId;

    //单词所属语言
    Language language;

    //是否在计划表中
    RecitalPlan plan;

    //创建时间
    Date creatAt;
}
