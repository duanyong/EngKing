package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.domain.audit.Auditable;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "user_words",
        indexes = {
                @Index(name = "IDX_USER_WORD",            columnList = "creator_by,word_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UNQ_SOURCE_WORD_CREATOR",       columnNames = {"source_id", "word_id", "creator_by"})
        })
public class UserWord extends Auditable<Integer> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "id",                        columnDefinition = "INT UNSIGNED")
    Integer id;

    @ApiModelProperty(notes = "源主键")
    @JsonProperty("source_id")
    @Column(name = "source_id",                 columnDefinition = "INT UNSIGNED NOT NULL COMMENT '源主键'")
    Integer sourceId;

    @ApiModelProperty(notes = "单词主键")
    @JsonProperty("word_id")
    @Column(name = "word_id",                   columnDefinition = "INT UNSIGNED NOT NULL COMMENT '单词主键'")
    Integer wordId;
}