package com.vladislav.crm.communications.handlers;

import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.services.operations.DeleteOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDeleteEntityRequestHandler<T extends AbstractEntity>
        implements RequestHandler<Long, Void> {

    private final DeleteOperation<T> deleteOperation;

    @Override
    public Void handle(Long id) {
        deleteOperation.execute(id);
        return null;
    }
}
