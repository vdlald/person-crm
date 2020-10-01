package com.vladislav.crm.services.operations.abstractions;

import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.services.operations.CreateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
public abstract class AbstractCreateOperation<T extends AbstractEntity> implements CreateOperation<T> {

    private final JpaRepository<T, Long> repository;

    @Override
    public T execute(T entity) {
        entity.setId(null);
        return repository.save(entity);
    }
}
