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
@Table(name = "Words",
    indexes = {
            @Index(name = "IDX_NAME",            columnList = "name")
    },
    uniqueConstraints = {
            @UniqueConstraint(name = "UNQ_NAME_LANGUAGE",       columnNames = {"name", "language"})
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

    @ApiModelProperty(notes = "语种")
    @Enumerated(EnumType.STRING)
    @JsonProperty("language")
    @Column(name = "language",                  columnDefinition = "VARCHAR(32) NOT NULL COMMENT '语种'")
    Language language;
}