package com.vladislav.crm.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "createdAt", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updatedAt")
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime updatedAt;

    @PreUpdate
    private void setUpdatedAt() {
        updatedAt = LocalDateTime.now();
    }
}
