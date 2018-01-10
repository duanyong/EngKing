package com.reaier.engking.bean;

import com.reaier.engking.dao.model.Entity;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "user_source")
public class UserSource extends Entity {
    private Object id;

    private Integer sourceId;

    private Integer userId;

    private Date insertTime;

    private String insertText;

    private Boolean insertType;

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
        UserSource other = (UserSource) that;
        return (this.getSourceId() == null ? other.getSourceId() == null : this.getSourceId().equals(other.getSourceId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getInsertText() == null ? other.getInsertText() == null : this.getInsertText().equals(other.getInsertText()))
            && (this.getInsertType() == null ? other.getInsertType() == null : this.getInsertType().equals(other.getInsertType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSourceId() == null) ? 0 : getSourceId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getInsertText() == null) ? 0 : getInsertText().hashCode());
        result = prime * result + ((getInsertType() == null) ? 0 : getInsertType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sourceId=").append(sourceId);
        sb.append(", userId=").append(userId);
        sb.append(", insertTime=").append(insertTime);
        sb.append(", insertText=").append(insertText);
        sb.append(", insertType=").append(insertType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}