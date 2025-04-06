package org.nexthope.doctorsuite.commons;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class BaseAudit {

    @CreatedDate
    @Column(name = "creation_date", nullable = false, updatable = false, insertable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(name = "modification_date", insertable = false)
    private LocalDateTime modificationDate;

}
