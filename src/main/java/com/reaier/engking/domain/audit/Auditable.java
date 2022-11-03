package com.reaier.engking.domain.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reaier.engking.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter(AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",                        columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '项目主键'")
    Integer id;

    @CreatedBy
    @JsonIgnore
    @Column(name = "creator_by",                columnDefinition = "INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '创建者账号主键'")
    U creatorBy;

    @JsonIgnore
    @Column(name = "update_at",                 columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP() COMMENT '最后更新时间'", insertable = false, updatable = false)
    Date updateAt;

    @LastModifiedBy
    @JsonIgnore
    @Column(name = "modifier_by",               columnDefinition = "INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '修改者账号主键'")
    U modifierBy;

    @JsonIgnore
    @Column(name = "update_at",                 columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP() COMMENT '最后更新时间'", insertable = false, updatable = false)
    Date modifyAt;

    @PrePersist
    public void prePersist() {
        User creator = getUsernameOfAuthenticatedUser();

        this.creatorBy = (U) creator.getId();
    }

    @PreUpdate
    public void preUpdate() {
        User modifier = getUsernameOfAuthenticatedUser();

        this.modifierBy = (U) modifier.getId();
    }

    private User getUsernameOfAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
//            return null;
            return User.builder().id(1).username("Tucker Duan").build();
        }

        return ((User) authentication.getCredentials());
    }
}
