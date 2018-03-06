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

    //小程序ID
    @Column(name = "nickname", nullable = false, updatable = false, length = 32)
    String username;

    //头像
    @Column(name = "avatar", length = 128)
    String avatar;

    public User() {
        super(null, false);
    }

    public User(String openid, Boolean isUpdate) {
        super(openid, isUpdate);
    }

}
