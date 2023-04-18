package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.audit.Auditable;
import com.reaier.engking.domain.convert.JSONConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Sentences",
        indexes = {
                @Index(name = "IDX_TOKEN",            columnList = "token")
        },
        uniqueConstraints = {
//                @UniqueConstraint(name = "UNQ_TOKEN",       columnNames = {"token"})
        })
public class Sentence extends Auditable<Integer> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "id",                        columnDefinition = "INT UNSIGNED")
    Long id;

    @ApiModelProperty(notes = "原生语音")
    @Enumerated(EnumType.STRING)
    @JsonProperty("origin")
    @Column(name = "origin",                    columnDefinition = "VARCHAR(32) NOT NULL COMMENT '语种'")
    Language origin;

    @ApiModelProperty(notes = "目标语音")
    @Enumerated(EnumType.STRING)
    @JsonProperty("target")
    @Column(name = "target",                    columnDefinition = "VARCHAR(32) NOT NULL COMMENT '目标语音'")
    Language target;

    @ApiModelProperty(notes = "句子")
    @JsonProperty("sentence")
    @Column(name = "sentence",                  columnDefinition = "TEXT NULL COMMENT '处理的内容'")
    String sentence;

    @ApiModelProperty(notes = "翻译的句子")
    @JsonProperty("translate")
    @Column(name = "translate",                 columnDefinition = "TEXT NULL COMMENT '翻译的句子'")
    String translate;

    @Convert(converter = JSONConverter.StringListConverter.class)
    @ApiModelProperty(notes = "关键单词")
    @JsonProperty("key_words")
    @Column(name = "key_words",                 columnDefinition = "JSON NULL COMMENT '关键字'")
    List<String> keyWords;

    @Convert(converter = JSONConverter.StringListConverter.class)
    @ApiModelProperty(notes = "关键句型")
    @JsonProperty("key_phrases")
    @Column(name = "key_phrases",               columnDefinition = "JSON NULL COMMENT '关键句型'")
    List<String> keyPhrases;

    @Version
    @Column(name = "version",                   columnDefinition = "INT(10) UNSIGNED DEFAULT '0' COMMENT '版本号处理'")
    Integer version;
}
