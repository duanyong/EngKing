package com.reaier.engking.sequence.dictionary.ecdict;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.dictionary.Phonics;
import com.reaier.engking.dictionary.Translation;
import com.reaier.engking.domain.audit.Auditable;
import com.reaier.engking.domain.convert.JSONConverter;
import com.reaier.engking.utils.JsonUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@Table(name = "words")
public class ECDict extends Auditable<Integer> implements Serializable {
    @Id
    @ApiModelProperty(notes = "单词")
    @JsonProperty("word")
    @Column(name = "word",                      columnDefinition = "VARCHAR(64) NOT NULL COMMENT '单词'")
    String word;

    @ApiModelProperty(notes = "音标，以英语英标为主")
    @JsonProperty("phonetic")
    @Column(name = "phonetic",                   columnDefinition = "VARCHAR(512) NULL COMMENT '音标，以英语英标为主'")
    String phonetic;

    @ApiModelProperty(notes = "单词释义（英文），每行一个释义")
    @JsonProperty("definition")
    @Column(name = "definition",                columnDefinition = "VARCHAR(2048) NULL COMMENT '单词释义（英文），每行一个释义'")
    String definition;

    @ApiModelProperty(notes = "单词释义（中文），每行一个释义")
    @JsonProperty("translation")
    @Column(name = "translation",               columnDefinition = "VARCHAR(2048) NULL COMMENT '单词释义（中文），每行一个释义'")
    String translation;


    @ApiModelProperty(notes = "时态复数等变换，使用 \"/\" 分割不同项目，见后面表格")
    @JsonProperty("pos")
    @Column(name = "pos",                       columnDefinition = "VARCHAR(2048) NULL COMMENT '时态复数等变换，使用 \"/\" 分割不同项目，见后面表格'")
    String pos;

    @ApiModelProperty(notes = "时态复数等变换，使用 \"/\" 分割不同项目，见后面表格")
    @JsonProperty("exchange")
    @Column(name = "exchange",                  columnDefinition = "VARCHAR(2048) NULL COMMENT '时态复数等变换，使用 \"/\" 分割不同项目，见后面表格'")
    String exchange;
}