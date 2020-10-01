package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.AbstractEntity;

public interface DeleteOperation<T extends AbstractEntity> {
    void execute(Long id);

    default void execute(T entity) {
        execute(entity.getId());
    }
}
