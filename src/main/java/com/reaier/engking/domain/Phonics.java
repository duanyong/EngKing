package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.Dialect;
import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.audit.Auditable;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Phonics",
        indexes = {
                @Index(name = "IDX_WORD_LANGUAGE_DIALECT",            columnList = "word_id,language,dialect")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UNQ_WORD_LANGUAGE_DIALECT",       columnNames = {"word_id", "language", "dialect"})
        })
public class Phonics extends Auditable<Integer> {
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

    @ApiModelProperty(notes = "单词方言")
    @Enumerated(EnumType.STRING)
    @JsonProperty("dialect")
    @Column(name = "dialect",                   columnDefinition = "VARCHAR(32) NULL COMMENT '单词方言'")
    Dialect dialect;

    @ApiModelProperty(notes = "音标")
    @JsonProperty("phonetics")
    @Column(name = "phonetics",                 columnDefinition = "VARCHAR(32) NULL COMMENT '音标'")
    String phonetics;

    @ApiModelProperty(notes = "发音地址")
    @JsonProperty("sound")
    @Column(name = "sound",                     columnDefinition = "VARCHAR(512) NULL COMMENT '发音地址'")
    String sound;
}
