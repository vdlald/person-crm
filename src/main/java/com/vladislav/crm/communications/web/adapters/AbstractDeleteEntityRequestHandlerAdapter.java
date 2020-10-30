package com.vladislav.crm.communications.web.adapters;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.entities.AbstractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public abstract class AbstractDeleteEntityRequestHandlerAdapter<T extends AbstractEntity>
        implements RequestHandlerAdapter<Long, ResponseEntity<Void>> {

    private final AbstractDeleteEntityRequestHandler<T> requestHandler;

    @Override
    public ResponseEntity<Void> handle(Long id) {
        requestHandler.handle(id);
        return ResponseEntity.noContent().build();
    }
}
