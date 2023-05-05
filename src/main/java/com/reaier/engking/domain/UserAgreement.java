package com.reaier.engking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_agreement",
        indexes = {
                @Index(name = "IDX_USER_AGREEMENT",            columnList = "user_id, agreement_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UQN_USER_AGREEMENT",       columnNames = {"user_id", "agreement_id"})
        })
public class UserAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",                        columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '数据主键'")
    Integer id;

    @ApiModelProperty(notes = "登录账号")
    @JsonProperty("user_id")
    @Column(name = "user_id",                   columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '用户主键'")
    Integer userId;

    @ApiModelProperty(notes = "协议主键")
    @JsonProperty("agreement_id")
    @Column(name = "agreement_id",                  columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '协议主键'")
    Integer agreementId;

    @JsonIgnore
    @Column(name = "create_at",                 columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间'", insertable = false, updatable = false)
    Date createAt;
}
