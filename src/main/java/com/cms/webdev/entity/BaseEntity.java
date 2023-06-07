package com.cms.webdev.entity;

import java.io.Serializable;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@NoArgsConstructor
public abstract class BaseEntity<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -4650977692338418463L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Column(name = "created_on",updatable = false)
    @CreatedDate
    private OffsetDateTime createdOn;

    @LastModifiedDate
    @Column(name="updated_on",updatable = true)
    private OffsetDateTime updatedOn;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "is_deleted",columnDefinition = "tinyint(1) default 0")
    private Boolean isDeleted;

}
