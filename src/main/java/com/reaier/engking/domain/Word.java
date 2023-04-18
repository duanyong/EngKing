package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.Language;
import com.reaier.engking.dictionary.Phonics;
import com.reaier.engking.dictionary.Translation;
import com.reaier.engking.domain.audit.Auditable;
import com.reaier.engking.utils.JsonUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "Words",
    indexes = {
            @Index(name = "IDX_NAME",            columnList = "name")
    },
    uniqueConstraints = {
            @UniqueConstraint(name = "UNQ_NAME_ORIGIN_TARGET",       columnNames = {"name", "origin", "target"})
})
public class Word extends Auditable<Integer> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",                        columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '项目主键'")
    Long id;

    @ApiModelProperty(notes = "单词")
    @JsonProperty("name")
    @Column(name = "name",                      columnDefinition = "VARCHAR(64) NOT NULL COMMENT '单词'")
    String name;

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

    @Convert(converter = PhonicsConverter.class)
    @ApiModelProperty(notes = "语音数据")
    @JsonProperty("phonics")
    @Column(name = "phonics",                   columnDefinition = "JSON NULL COMMENT '语音数据'")
    List<Phonics> phonics;

    @Convert(converter = TranslationConverter.class)
    @ApiModelProperty(notes = "翻译数据")
    @JsonProperty("translation")
    @Column(name = "translation",               columnDefinition = "JSON NULL COMMENT '翻译数据'")
    List<Translation> translation;

    @Convert(converter = TranslationConverter.class)
    @ApiModelProperty(notes = "关键例句主键列表")
    @JsonProperty("sentence_ids")
    @Column(name = "sentence_ids",              columnDefinition = "JSON NULL COMMENT '关键例句主键列表'")
    List<Long> sentenceIds;

    @Convert(converter = TranslationConverter.class)
    @ApiModelProperty(notes = "关键短语主键列表")
    @JsonProperty("phrase_ids")
    @Column(name = "phrase_ids",                columnDefinition = "JSON NULL COMMENT '关键短语主键列表'")
    List<Long> phraseIds;

    @ApiModelProperty(notes = "词源")
    @JsonProperty("etymology")
    @Column(name = "etymology",                 columnDefinition = "VARCHAR(512) NULL COMMENT '词源'")
    String etymology;

    @Converter(
            autoApply = true
    )
    private static class PhonicsConverter implements AttributeConverter<List, String> {
        @Nullable
        public String convertToDatabaseColumn(List list) {
            return null == list ? null : JsonUtils.obj2Json(list);
        }

        @Nullable
        public List<Phonics> convertToEntityAttribute(String list) {
            return null == list ? null : JsonUtils.json2Obj(list, List.class, Phonics.class);
        }
    }

    @Converter(
            autoApply = true
    )
    private static class TranslationConverter implements AttributeConverter<List, String> {
        @Nullable
        public String convertToDatabaseColumn(List list) {
            return null == list ? null : JsonUtils.obj2Json(list);
        }

        @Nullable
        public List<Translation> convertToEntityAttribute(String list) {
            return null == list ? null : JsonUtils.json2Obj(list, List.class, Translation.class);
        }
    }
}