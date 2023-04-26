package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.domain.audit.Auditable;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users",
        indexes = {
                @Index(name = "IDX_USERNAME",            columnList = "username")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UQN_USERNAME",       columnNames = {"username"})
        })
public class User extends Auditable<Integer> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",                        columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '项目主键'")
    Integer id;

    @ApiModelProperty(notes = "登录账号")
    @JsonProperty("username")
    @Column(name = "username",                  columnDefinition = "CHAR(32) NOT NULL COMMENT '登录账号'")
    String username;

    @ApiModelProperty(notes = "登录密码")
    @JsonProperty("password")
    @Column(name = "password",                  columnDefinition = "CHAR(32) NOT NULL COMMENT '登录密码'")
    String password;
}
