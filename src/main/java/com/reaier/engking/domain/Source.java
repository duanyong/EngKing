package com.reaier.engking.domain;

import com.reaier.engking.constants.Language;
import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.constants.SourceType;
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
public class Source implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //用户主键
    Integer userId;

    //待翻译内容
    String content;

    //翻译语音
    Language language;

    //待翻译类别
    SourceType type;

    //当前状态
    WordProcess status;

    Date time;
}
