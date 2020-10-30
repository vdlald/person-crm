package com.vladislav.crm.communications.web.adapters;

import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.services.operations.DeleteOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public abstract class AbstractDeleteEntityRequestHandler<T extends AbstractEntity>
        implements RequestHandlerAdapter<Long, ResponseEntity<Void>> {

    private final DeleteOperation<T> deleteOperation;

    @Override
    public ResponseEntity<Void> handle(Long id) {
        deleteOperation.execute(id);
        return ResponseEntity.noContent().build();
    }
}
