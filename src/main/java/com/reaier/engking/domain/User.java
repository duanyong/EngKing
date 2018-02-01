package com.reaier.engking.domain;

import com.reaier.core.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;


@Data
@Entity
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    ////////////////////////////////////////////////////////////////////////////////
    //请求部分
    //

    //小程序ID
    @Column(name = "nickname", nullable = false, updatable = false, length = 32)
    String username;

    //商户号
    @Column(name = "password", nullable = false, updatable = false, length = 32)
    String password;

    //商户号
    @Column(name = "password", nullable = false, updatable = false, length = 32)
    String password;

    //
    //请求部分
    ////////////////////////////////////////////////////////////////////////////////


    protected User(String openid, Boolean isUpdate) {
        super(openid, isUpdate);
    }

}
