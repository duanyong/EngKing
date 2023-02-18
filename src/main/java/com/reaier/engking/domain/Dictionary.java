package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.Dialect;
import com.reaier.engking.constants.Language;
import com.reaier.engking.dictionary.Phonics;
import com.reaier.engking.dictionary.Translation;
import com.reaier.engking.domain.audit.Auditable;
import com.reaier.engking.domain.convert.JSONConverter;
import com.reaier.engking.utils.JsonUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


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
public class Dictionary extends Auditable<Integer> implements Serializable {
    @Id
    @ApiModelProperty(notes = "单词主键")
    @JsonProperty("word_id")
    @Column(name = "word_id",                   columnDefinition = "INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '单词主键'")
    Integer wordId;

    @Id
    @ApiModelProperty(notes = "语种")
    @Enumerated(EnumType.STRING)
    @JsonProperty("language")
    @Column(name = "language",                  columnDefinition = "VARCHAR(32) NOT NULL COMMENT '语种'")
    Language language;

    @Convert(converter = PhonicsConverter.class)
    @ApiModelProperty(notes = "语法数据")
    @JsonProperty("phonics")
    @Column(name = "phonics",                   columnDefinition = "JSON NULL COMMENT '语法数据'")
    List<Phonics> phonics;

    @Convert(converter = TranslationConverter.class)
    @ApiModelProperty(notes = "翻译数据")
    @JsonProperty("translation")
    @Column(name = "translation",               columnDefinition = "JSON NULL COMMENT '翻译数据'")
    List<Translation> translation;


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
