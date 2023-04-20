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

@Table(name = "Phrases",
        indexes = {
                @Index(name = "IDX_TOKEN",            columnList = "token")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UNQ_TOKEN",       columnNames = {"token"})
        })
public class Phrase extends Auditable<Integer> implements Serializable {
    @Id
    @JsonProperty("token")
    @Column(name = "token",                     columnDefinition = "INT UNSIGNED NOT NULL COMMENT '短语主键'")
    Long token;

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

    @ApiModelProperty(notes = "短语")
    @JsonProperty("phrase")
    @Column(name = "phrase",                    columnDefinition = "TEXT NULL COMMENT '短语'")
    String phrase;

    @ApiModelProperty(notes = "翻译的短语")
    @JsonProperty("translate")
    @Column(name = "translate",                 columnDefinition = "TEXT NULL COMMENT '翻译的短语'")
    String translate;
//
//    @ApiModelProperty(notes = "相似词")
//    @JsonProperty("close")
//    @Column(name = "close",                     columnDefinition = "VARCHAR(1024) NULL COMMENT '短语'")
//    String close;

    @Version
    @Column(name = "version",                   columnDefinition = "INT(10) UNSIGNED DEFAULT '0' COMMENT '版本号处理'")
    Integer version;
}
