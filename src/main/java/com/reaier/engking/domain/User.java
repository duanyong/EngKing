package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Date;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        indexes = {
                @Index(name = "IDX_PLATFORM_OPENID",            columnList = "platform, openid")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UQN_PLATFORM_OPENID",       columnNames = {"platform", "openid"})
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",                        columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '项目主键'")
    Integer id;

    @ApiModelProperty(notes = "登录账号")
    @JsonProperty("platform")
    @Column(name = "platform",                  columnDefinition = "CHAR(32) NOT NULL COMMENT '账号平台'")
    String platform;

    @ApiModelProperty(notes = "平台用户识别码")
    @JsonProperty("openid")
    @Column(name = "openid",                    columnDefinition = "VARCHAR(256) NOT NULL COMMENT '平台用户识别码'")
    String openid;

//    @ApiModelProperty(notes = "登录账号")
//    @JsonProperty("username")
//    @Column(name = "username",                  columnDefinition = "VARCHAR(1024) NOT NULL COMMENT '登录账号'")
//    String username;
//
//    @ApiModelProperty(notes = "账号类型")
//    @JsonProperty("account_type")
//    @Column(name = "account_type",              columnDefinition = "VARCHAR(32) NOT NULL COMMENT '账号类型'")
//    AccountType accountType;
//
//    @ApiModelProperty(notes = "登录密码")
//    @JsonProperty("password")
//    @Column(name = "password",                  columnDefinition = "CHAR(32) NOT NULL COMMENT '登录密码'")
//    String password;

    @ApiModelProperty(notes = "账号昵称")
    @JsonProperty("nickname")
    @Column(name = "nickname",                  columnDefinition = "VARCHAR(256) NOT NULL COMMENT '账号昵称'")
    String nickname;

    @JsonIgnore
    @Column(name = "create_at",                 columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间'", insertable = false, updatable = false)
    Date createAt;

    @JsonIgnore
    @Column(name = "update_at",                 columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP() COMMENT '最后更新时间'", insertable = false, updatable = false)
    Date updateAt;
}
