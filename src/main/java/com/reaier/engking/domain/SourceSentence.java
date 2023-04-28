package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.domain.audit.Auditable;
import com.reaier.engking.domain.convert.JSONConverter;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "source_sentences",
        indexes = {
                @Index(name = "IDX_USER_WORD",            columnList = "creator_by,word_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UNQ_SOURCE_WORD_CREATOR",       columnNames = {"source_id", "word_id", "creator_by"})
        })
public class SourceSentence extends Auditable<Integer> implements Serializable {
    @Id
    @JsonProperty("token")
    @Column(name = "token",                     columnDefinition = "INT UNSIGNED")
    Long token;

    @ApiModelProperty(notes = "源主键")
    @JsonProperty("source_id")
    @Column(name = "source_id",                 columnDefinition = "INT UNSIGNED NOT NULL COMMENT '源主键'")
    Integer sourceId;

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

    @Convert(converter = JSONConverter.IntegerListConverter.class)
    @ApiModelProperty(notes = "单词列表")
    @JsonProperty("word_ids")
    @Column(name = "word_ids",                  columnDefinition = "JSON NULL COMMENT '关键字'")
    List<Integer> wordIds;

    @Version
    @Column(name = "version",                   columnDefinition = "INT(10) UNSIGNED DEFAULT '0' COMMENT '版本号处理'")
    Integer version;
}