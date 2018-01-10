package com.reaier.engking.bean;

import com.reaier.engking.dao.model.Entity;
import lombok.Data;

import javax.persistence.Table;


@Data
@Table(name = "words")
public class Words extends Entity {
    private Object id;

    private Integer wordId;

    private String word;

    private Byte grade;

    private Boolean completeStatus;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Words other = (Words) that;
        return (this.getWordId() == null ? other.getWordId() == null : this.getWordId().equals(other.getWordId()))
            && (this.getWord() == null ? other.getWord() == null : this.getWord().equals(other.getWord()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getCompleteStatus() == null ? other.getCompleteStatus() == null : this.getCompleteStatus().equals(other.getCompleteStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWordId() == null) ? 0 : getWordId().hashCode());
        result = prime * result + ((getWord() == null) ? 0 : getWord().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getCompleteStatus() == null) ? 0 : getCompleteStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wordId=").append(wordId);
        sb.append(", word=").append(word);
        sb.append(", grade=").append(grade);
        sb.append(", completeStatus=").append(completeStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}