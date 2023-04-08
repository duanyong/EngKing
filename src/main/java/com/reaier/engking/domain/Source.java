package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.Language;
import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.audit.Auditable;
import com.reaier.engking.domain.convert.JSONConverter;
import com.reaier.engking.sequence.ocr.describe.Coordinate;
import com.reaier.engking.sequence.ocr.describe.Point;
import com.reaier.engking.utils.JsonUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Sources",
        indexes = {
                @Index(name = "IDX_TOKEN",            columnList = "token")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UNQ_TOKEN",       columnNames = {"token"})
        })
public class Source extends Auditable<Integer> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "id",                        columnDefinition = "INT UNSIGNED")
    Long id;

    @ApiModelProperty(notes = "从哪种语言翻译到目标语言：哪种语言")
    @Enumerated(EnumType.STRING)
    @JsonProperty("origin")
    @Column(name = "origin",                    columnDefinition = "VARCHAR(32) NOT NULL COMMENT '从哪种语言翻译到目标语言：哪种语言'")
    Language origin;

    @ApiModelProperty(notes = "从哪种语言翻译到目标语言：目标语言")
    @Enumerated(EnumType.STRING)
    @JsonProperty("target")
    @Column(name = "target",                    columnDefinition = "VARCHAR(32) NOT NULL COMMENT '从哪种语言翻译到目标语言：目标语言'")
    Language target;

    @ApiModelProperty(notes = "内容标识")
    @JsonProperty("token")
    @Column(name = "token",                     columnDefinition = "CHAR(32) NULL COMMENT '内容标识'")
    String token;

    @ApiModelProperty(notes = "处理的内容")
    @JsonProperty("content")
    @Column(name = "content",                   columnDefinition = "TEXT NULL COMMENT '处理的内容'")
    String content;

    @ApiModelProperty(notes = "用于存储是图片地址或网址")
    @JsonProperty("source")
    @Column(name = "`source`",                 columnDefinition = "VARCHAR(512) NULL COMMENT '用于存储是图片地址或网址'")
    String source;

    @Convert(converter = CoordinateConverter.class)
    @ApiModelProperty(notes = "如果是图片，将存储图片中对应单词的坐标")
    @JsonProperty("coordinate")
    @Column(name = "Coordinate",                columnDefinition = "JSON NULL COMMENT '如果是图片，将存储图片中对应单词的坐标'")
    List<Coordinate> coordinate;

    @Convert(converter = LemmaConverter.class)
    @ApiModelProperty(notes = "词素（单词原型）")
    @JsonProperty("lemma")
    @Column(name = "lemma",                     columnDefinition = "JSON NULL COMMENT '词素（单词原型）'")
    Set<Word> lemma;

    @ApiModelProperty(notes = "上传的类型：图片，文字等")
    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    @Column(name = "`type`",                    columnDefinition = "VARCHAR(32) NOT NULL COMMENT '上传的类型：图片，文字等'")
    SourceType type;

    @Convert(converter = CoordinateConverter.class)
    @ApiModelProperty(notes = "处理流程")
    @Enumerated(EnumType.STRING)
    @JsonProperty("process_status")
    @Column(name = "process_status",            columnDefinition = "JSON NULL COMMENT '处理流程'")
    List<SourceProcess> processStatus;

    @ApiModelProperty(notes = "处理进度：未处理，处理中，已处理")
    @Enumerated(EnumType.STRING)
    @JsonProperty("process_state")
    @Column(name = "process_state",            columnDefinition = "VARCHAR(32) NOT NULL COMMENT '处理进度：未处理，处理中，已处理'")
    SourceProcess processState;

    @Version
    @Column(name = "version",                   columnDefinition = "INT(10) UNSIGNED DEFAULT '0' COMMENT '版本号处理'")
    Integer version;

    @Converter(
            autoApply = true
    )
    private static class CoordinateConverter implements AttributeConverter<List, String> {
        @Nullable
        public String convertToDatabaseColumn(List list) {
            return null == list ? null : JsonUtils.obj2Json(list);
        }

        @Nullable
        public List<Coordinate> convertToEntityAttribute(String list) {
            return null == list ? null : JsonUtils.json2Obj(list, List.class, Coordinate.class, Point.class);
        }
    }

    @Converter(
            autoApply = true
    )
    private static class LemmaConverter implements AttributeConverter<Set, String> {
        @Nullable
        public String convertToDatabaseColumn(Set set) {
            return null == set ? null : JsonUtils.obj2Json(set);
        }

        @Nullable
        public Set<Word> convertToEntityAttribute(String set) {
            return null == set ? null : JsonUtils.json2Obj(set, HashSet.class, Word.class);
        }
    }
}
