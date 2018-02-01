package com.reaier.engking.domain;

import com.reaier.core.domain.BaseEntity;
import com.reaier.engking.constants.PartOfSpeech;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;


@Data
@Entity
public class EnToCnDetail extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    ////////////////////////////////////////////////////////////////////////////////
    //请求部分
    //

    //小程序ID
    @Column(name = "name", nullable = false, updatable = false, length = 32)
    String name;

    //商户号
    @Column(name = "part_of_speech", nullable = false, updatable = false, length = 32)
    PartOfSpeech partOfSpeech;



    //
    //请求部分
    ////////////////////////////////////////////////////////////////////////////////


    protected EnToCnDetail(String openid, Boolean isUpdate) {
        super(openid, isUpdate);
    }

}
