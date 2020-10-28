package com.vladislav.crm.services.operations.abstractions;

import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.services.operations.DeleteOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
public abstract class AbstractDeleteOperation<T extends AbstractEntity> implements DeleteOperation<T> {

    private final JpaRepository<T, Long> repository;

    @Override
    public void execute(@NonNull Long id) {
        repository.deleteById(id);
    }
}
