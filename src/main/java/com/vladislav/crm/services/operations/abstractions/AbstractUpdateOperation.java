package com.vladislav.crm.services.operations.abstractions;

import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.services.operations.UpdateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
public abstract class AbstractUpdateOperation<T extends AbstractEntity> implements UpdateOperation<T> {

    private final JpaRepository<T, Long> repository;

    @Override
    public T execute(T entity) {
        if (repository.existsById(entity.getId())) {
            return repository.save(entity);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
