package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.domain.audit.Auditable;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "words",
    indexes = {
            @Index(name = "IDX_NAME",            columnList = "name")
    },
    uniqueConstraints = {
            @UniqueConstraint(name = "UNQ_NAME",       columnNames = {"name"})
})
public class Word extends Auditable<Integer> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",                        columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '项目主键'")
    Integer id;

    @ApiModelProperty(notes = "单词")
    @JsonProperty("name")
    @Column(name = "name",                      columnDefinition = "VARCHAR(64) NOT NULL COMMENT '单词'")
    String name;

    @ApiModelProperty(notes = "语音数据")
    @JsonProperty("phonics")
    @Column(name = "phonics",                   columnDefinition = "VARCHAR(512) NULL COMMENT '语音数据'")
    String phonics;

    @ApiModelProperty(notes = "单词解说，不同类型的意思采用\n分隔")
    @JsonProperty("definition")
    @Column(name = "definition",               columnDefinition = "VARCHAR(2048) NULL COMMENT '单词解说，不同类型的意思采用\n分隔'")
    String definition;

    @ApiModelProperty(notes = "单词解释，不同类型的意思采用\n分隔")
    @JsonProperty("translation")
    @Column(name = "translation",               columnDefinition = "VARCHAR(2048) NULL COMMENT '单词解释，不同类型的意思采用\n分隔'")
    String translation;
//
//    @Convert(converter = JSONConverter.LongListConverter.class)
//    @ApiModelProperty(notes = "关键短语主键列表")
//    @JsonProperty("phrase_ids")
//    @Column(name = "phrase_ids",                columnDefinition = "JSON NULL COMMENT '关键短语主键列表'")
//    List<Long> phraseIds;
//
//    @Convert(converter = JSONConverter.LongListConverter.class)
//    @ApiModelProperty(notes = "关键例句主键列表")
//    @JsonProperty("sentence_ids")
//    @Column(name = "sentence_ids",              columnDefinition = "JSON NULL COMMENT '关键例句主键列表'")
//    List<Long> sentenceIds;
//
//
//    @ApiModelProperty(notes = "词源")
//    @JsonProperty("etymology")
//    @Column(name = "etymology",                 columnDefinition = "VARCHAR(512) NULL COMMENT '词源'")
//    String etymology;
//
//    @ApiModelProperty(notes = "人工精修过，自动翻译类无法处理，默认为false")
//    @JsonProperty("is_refine")
//    @Column(name = "is_refine",                 columnDefinition = "TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '人工精修过，自动翻译类无法处理，默认为false'")
//    String isRefine;

    @Version
    @Column(name = "version",                   columnDefinition = "INT(10) UNSIGNED DEFAULT '0' COMMENT '版本号处理'")
    Integer version;

//    @Converter(
//            autoApply = true
//    )
//    private static class PhonicsConverter implements AttributeConverter<List, String> {
//        @Nullable
//        public String convertToDatabaseColumn(List list) {
//            return null == list ? null : JsonUtils.obj2Json(list);
//        }
//
//        @Nullable
//        public List<Phonics> convertToEntityAttribute(String list) {
//            return null == list ? null : JsonUtils.json2Obj(list, List.class, Phonics.class);
//        }
//    }
//
//    @Converter(
//            autoApply = true
//    )
//    private static class TranslationConverter implements AttributeConverter<List, String> {
//        @Nullable
//        public String convertToDatabaseColumn(List list) {
//            return null == list ? null : JsonUtils.obj2Json(list);
//        }
//
//        @Nullable
//        public List<Translation> convertToEntityAttribute(String list) {
//            return null == list ? null : JsonUtils.json2Obj(list, List.class, Translation.class);
//        }
//    }
}