package com.reaier.engking.domain;

import com.reaier.core.domain.BaseEntity;
import com.reaier.engking.constants.Language;
import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;


@Data
@Builder
@Entity
public class Source extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户主键
    @Column(name = "user_id", nullable = false, updatable = false, length = 32)
    Integer userId;

    //待翻译内容
    @Column(name = "content", nullable = false, updatable = false, length = 32)
    String content;

    //翻译语音
    @Column(name = "language", nullable = false, updatable = false, length = 32)
    Language language;

    //待翻译类别
    @Column(name = "type", nullable = false, updatable = false, length = 32)
    SourceType type;

    //当前状态
    @Column(name = "process_status", nullable = false, updatable = false, length = 1)
    SourceProcess proccessStatus;


    protected Source(Integer userId) {
        super(userId.toString(), false);
    }

}
