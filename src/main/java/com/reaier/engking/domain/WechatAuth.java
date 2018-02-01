package com.reaier.engking.domain;

import com.reaier.core.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;


@Data
@Entity
public class WechatAuth extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //小程序ID
    @Column(name = "unique_id", nullable = false, updatable = false, length = 128)
    String uniqueId;

    //小程序ID
    @Column(name = "openid", nullable = false, updatable = false, length = 128)
    String name;

    //小程序ID
    @Column(name = "user_id", nullable = false, updatable = false)
    Integer userId;

    //商户号
    @Column(name = "avatar", nullable = false, updatable = false, length = 128)
    String avatar;


    protected WechatAuth(String openid, Boolean isUpdate) {
        super(openid, isUpdate);
    }

}
