package com.vladislav.crm.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AbstractEntityWithTime extends AbstractEntity {

    @Column(name = "createdAt", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    @Setter(AccessLevel.PRIVATE)
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
