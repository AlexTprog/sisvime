package com.sisvime.app.share;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AuditedEntity {

    @CreatedBy
    @Temporal(TemporalType.TIMESTAMP)
    protected Date CreatedDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date LastModify;

}
