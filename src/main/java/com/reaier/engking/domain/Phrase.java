package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.audit.Auditable;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "phrases",
        indexes = {
                @Index(name = "IDX_TOKEN",            columnList = "token")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UNQ_TOKEN",       columnNames = {"token"})
        })
public class Phrase extends Auditable<String> implements Serializable {
    @Id
    @JsonProperty("token")
    @Column(name = "token",                     columnDefinition = "CHAR(32) NOT NULL COMMENT '短语主键'")
    String token;

    @ApiModelProperty(notes = "短语")
    @JsonProperty("phrase")
    @Column(name = "phrase",                    columnDefinition = "VARCHAR(512) NULL COMMENT '短语'")
    String phrase;

    @ApiModelProperty(notes = "语音数据")
    @JsonProperty("phonics")
    @Column(name = "phonics",                   columnDefinition = "VARCHAR(512) NULL COMMENT '语音数据'")
    String phonics;

    @ApiModelProperty(notes = "翻译的短语")
    @JsonProperty("translation")
    @Column(name = "translation",               columnDefinition = "VARCHAR(512) NULL COMMENT '翻译的短语'")
    String translation;

    @ApiModelProperty(notes = "相似词")
    @JsonProperty("word_ids")
    @Column(name = "word_ids",                  columnDefinition = "VARCHAR(512) NULL COMMENT '短语'")
    String wordIds;

    @Version
    @Column(name = "version",                   columnDefinition = "INT(10) UNSIGNED DEFAULT '0' COMMENT '版本号处理'")
    Integer version;
}
