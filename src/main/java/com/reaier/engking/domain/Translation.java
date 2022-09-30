package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.audit.Auditable;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Translations",
        indexes = {
                @Index(name = "IDX_WORD_LANGUAGE",            columnList = "word_id, language")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UNQ_WORD_LANGUAGE",       columnNames = {"word_id", "language"})
        })
public class Translation extends Auditable<Integer> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",                        columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '项目主键'")
    Integer id;

    @ApiModelProperty(notes = "单词主键")
    @JsonProperty("word_id")
    @Column(name = "word_id",                   columnDefinition = "INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '单词主键'")
    Integer wordId;

    @ApiModelProperty(notes = "语种")
    @Enumerated(EnumType.STRING)
    @JsonProperty("language")
    @Column(name = "language",                  columnDefinition = "VARCHAR(32) NOT NULL COMMENT '语种'")
    Language language;

    @ApiModelProperty(notes = "翻译")
    @JsonProperty("translation")
    @Column(name = "translation",               columnDefinition = "TEXT NULL COMMENT '翻译'")
    String translation;
}
