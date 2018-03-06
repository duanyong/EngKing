package com.reaier.engking.domain;

import com.reaier.engking.constants.Language;
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
public class UserWord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //用户主键
    Integer userId;

    //来源主键
    Integer sourceId;

    //单词主键
    Integer wordId;

    //单词所属语言
    Language language;

    //创建时间
    Date creatAt;
}
