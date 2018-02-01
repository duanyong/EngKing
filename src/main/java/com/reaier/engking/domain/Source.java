package com.reaier.engking.domain;

import com.reaier.core.domain.BaseEntity;
import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;


@Data
@Entity
public class Source extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //小程序ID
    @Column(name = "content", nullable = false, updatable = false, length = 32)
    String content;

    //商户号
    @Column(name = "type", nullable = false, updatable = false, length = 32)
    SourceType type;

    //设备号
    @Column(name = "process_status", nullable = false, updatable = false, length = 1)
    SourceProcess proccessStatus;


    protected Source(String openid, Boolean isUpdate) {
        super(openid, isUpdate);
    }

}
