package com.reaier.engking.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "token", nullable = false, updatable = false, length = 32)
    String token;

    //当前登录平台
    @Column(name = "type", nullable = false, updatable = false, length = 32)
    String password;

    //当前平台的用户主键
    @Column(name = "user_id", nullable = false, updatable = false)
    Integer userId;

    //登录时间
    @Column(name = "in_time", nullable = false, updatable = false, length = 32)
    Date inTime;

    //过期时间
    @Column(name = "expire_time", nullable = false, updatable = false, length = 32)
    Date expireTime;
}
