package com.reaier.engking.domain.word;

import com.reaier.engking.constants.WordProcess;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EnWord extends Word implements Serializable {
    private static final long serialVersionUID = 1L;

    //来源主键
    Integer sourceId;

    WordProcess status;

    @Builder(toBuilder = true)
    public EnWord(String word, String phonetic, Integer sourceId, WordProcess status) {
        super(null, word, phonetic, word.hashCode());
        this.sourceId = sourceId;
        this.status = status;
    }
}
