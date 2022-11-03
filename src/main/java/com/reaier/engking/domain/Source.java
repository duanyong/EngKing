package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceType;
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
    private Integer id;

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
    @Column(name = "source",                   columnDefinition = "VARCHAR(512) NULL COMMENT '用于存储是图片地址或网址'")
    String source;

    @ApiModelProperty(notes = "上传的类型：图片，文字等")
    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    @Column(name = "type",                      columnDefinition = "VARCHAR(32) NOT NULL COMMENT '上传的类型：图片，文字等'")
    SourceType type;

    @ApiModelProperty(notes = "处理进度：未处理，处理中，已处理")
    @Enumerated(EnumType.STRING)
    @JsonProperty("process_status")
    @Column(name = "process_status",            columnDefinition = "VARCHAR(32) NOT NULL COMMENT '处理进度：未处理，处理中，已处理'")
    SourceProcess processStatus;
}
