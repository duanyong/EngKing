package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.domain.audit.Auditable;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agreements",
        indexes = {
//                @Index(name = "IDX_USERNAME",            columnList = "username")
        },
        uniqueConstraints = {
//                @UniqueConstraint(name = "UQN_USERNAME",       columnNames = {"username"})
        })
public class Agreement extends Auditable<Integer> implements Serializable {
    @Id
    @Column(name = "id",                        columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '协议主键'")
    Integer id;

    @ApiModelProperty(notes = "协议内容")
    @JsonProperty("agreement")
    @Column(name = "agreement",                 columnDefinition = "TEXT NULL COMMENT '协议内容'")
    String agreement;

    @ApiModelProperty(notes = "协议版本")
    @JsonProperty("version")
    @Column(name = "version",                  columnDefinition = "VARCHAR(10) NOT NULL COMMENT '协议版本'")
    String version;
}