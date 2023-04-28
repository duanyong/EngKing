package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.audit.Auditable;
import com.reaier.engking.sequence.ocr.describe.Coordinate;
import com.reaier.engking.sequence.source.Text;
import com.reaier.engking.utils.JsonUtils;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.*;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


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
    Integer id;

    @ApiModelProperty(notes = "上传的类型：图片，文字等")
    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    @Column(name = "`type`",                    columnDefinition = "VARCHAR(32) NOT NULL COMMENT '上传的类型：图片，文字等'")
    SourceType type;

    @ApiModelProperty(notes = "内容标识")
    @JsonProperty("token")
    @Column(name = "token",                     columnDefinition = "CHAR(32) NULL COMMENT '内容标识'")
    String token;

    @ApiModelProperty(notes = "用于存储是图片地址或网址")
    @JsonProperty("source")
    @Column(name = "source",                    columnDefinition = "VARCHAR(512) NULL COMMENT '用于存储是图片地址或网址'")
    String source;

    // 通过OCR识别出来的文本内容，包含短句和单词列表，单词包括对应的位置信息
    @Convert(converter = TextConverter.class)
    @ApiModelProperty(notes = "通过OCR识别出来的文本内容")
    @JsonProperty("texts")
    @Column(name = "texts",                     columnDefinition = "JSON NULL COMMENT '通过OCR识别出来的文本内容'")
    List<Text> texts;

    @Convert(converter = LemmaConverter.class)
    @ApiModelProperty(notes = "词素（单词原型）")
    @JsonProperty("lemmas")
    @Column(name = "lemmas",                    columnDefinition = "JSON NULL COMMENT '词素（单词原型）'")
    Map<String, String> lemmas;

    @Convert(converter = CoordinateConverter.class)
    @ApiModelProperty(notes = "如果是图片，将存储图片中对应单词的坐标")
    @JsonProperty("coordinates")
    @Column(name = "Coordinates",               columnDefinition = "JSON NULL COMMENT '如果是图片，将存储图片中对应单词的坐标'")
    List<Coordinate> coordinates;

    @Convert(converter = ProcessConverter.class)
    @ApiModelProperty(notes = "处理流程")
    @JsonProperty("processes")
    @Column(name = "processes",                 columnDefinition = "JSON NULL COMMENT '处理流程'")
    List<SourceProcess> processes;

    @ApiModelProperty(notes = "当前处理流程")
    @Enumerated(EnumType.STRING)
    @JsonProperty("current_process")
    @Column(name = "current_process",           columnDefinition = "VARCHAR(32) NOT NULL COMMENT '当前处理流程'")
    SourceProcess currentProcess;

    @ApiModelProperty(notes = "处理进度：未处理，处理中，已处理")
    @Enumerated(EnumType.STRING)
    @JsonProperty("process_status")
    @Column(name = "process_status",            columnDefinition = "VARCHAR(32) NOT NULL COMMENT '处理进度：未处理，处理中，已处理'")
    SourceProcessStatus processStatus;

    @Version
    @Column(name = "version",                   columnDefinition = "INT(10) UNSIGNED DEFAULT '0' COMMENT '版本号处理'")
    Integer version;

    @Converter(
            autoApply = true
    )
    private static class TextConverter implements AttributeConverter<List, String> {
        @Nullable
        public String convertToDatabaseColumn(List list) {
            return null == list ? null : JsonUtils.obj2Json(list);
        }

        @Nullable
        public List<Text> convertToEntityAttribute(String list) {
            return null == list ? null : JsonUtils.json2Obj(list, List.class, Text.class);
        }
    }

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
            return null == list ? null : JsonUtils.json2Obj(list, List.class, Coordinate.class);
        }
    }

    @Converter(
            autoApply = true
    )
    private static class LemmaConverter implements AttributeConverter<Map<String, String>, String> {
        @Override
        public String convertToDatabaseColumn(Map<String, String> string) {
            return null == string ? null : JsonUtils.obj2Json(string);
        }

        @Nullable
        public Map<String, String> convertToEntityAttribute(String set) {
            return null == set ? null : JsonUtils.json2Obj(set, Map.class);
        }
    }

    @Converter(
            autoApply = true
    )
    private static class ProcessConverter implements AttributeConverter<List, String> {
        @Nullable
        public String convertToDatabaseColumn(List list) {
            return null == list ? null : JsonUtils.obj2Json(list);
        }

        @Nullable
        public List<SourceProcess> convertToEntityAttribute(String list) {
            return null == list ? null : JsonUtils.json2Obj(list, List.class, SourceProcess.class);
        }
    }
}
