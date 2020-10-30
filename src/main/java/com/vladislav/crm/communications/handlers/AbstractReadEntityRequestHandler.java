package com.vladislav.crm.communications.handlers;

import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractReadEntityRequestHandler<T extends AbstractEntity>
        implements RequestHandler<Long, T> {

    private final ReadOperation<T> readOperation;

    @Override
    public T handle(Long id) {
        return readOperation.execute(id);
    }
}
