package com.vladislav.crm.communications.web.adapters;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.entities.AbstractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@RequiredArgsConstructor
public abstract class AbstractReadEntityRequestHandlerAdapter<T extends AbstractEntity, RES>
        implements RequestHandlerAdapter<Long, EntityModel<RES>> {

    private final RepresentationModelAssembler<T, EntityModel<RES>> assembler;
    private final AbstractReadEntityRequestHandler<T> requestHandler;

    @Override
    public EntityModel<RES> handle(Long id) {
        return assembler.toModel(requestHandler.handle(id));
    }
}
