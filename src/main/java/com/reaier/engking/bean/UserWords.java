package com.reaier.engking.bean;

import com.reaier.engking.dao.model.Entity;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "user_words")
public class UserWords extends Entity {
    private Object id;

    private Integer userWordId;

    private Integer sourceId;

    private Integer userId;

    private Integer wordId;

    private Integer translationId;

    private Date insertTime;

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
        UserWords other = (UserWords) that;
        return (this.getUserWordId() == null ? other.getUserWordId() == null : this.getUserWordId().equals(other.getUserWordId()))
            && (this.getSourceId() == null ? other.getSourceId() == null : this.getSourceId().equals(other.getSourceId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getWordId() == null ? other.getWordId() == null : this.getWordId().equals(other.getWordId()))
            && (this.getTranslationId() == null ? other.getTranslationId() == null : this.getTranslationId().equals(other.getTranslationId()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserWordId() == null) ? 0 : getUserWordId().hashCode());
        result = prime * result + ((getSourceId() == null) ? 0 : getSourceId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getWordId() == null) ? 0 : getWordId().hashCode());
        result = prime * result + ((getTranslationId() == null) ? 0 : getTranslationId().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userWordId=").append(userWordId);
        sb.append(", sourceId=").append(sourceId);
        sb.append(", userId=").append(userId);
        sb.append(", wordId=").append(wordId);
        sb.append(", translationId=").append(translationId);
        sb.append(", insertTime=").append(insertTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}