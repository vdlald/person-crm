package com.vladislav.crm.controllers.requesthandlers;

import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@RequiredArgsConstructor
public abstract class AbstractReadEntityRequestHandler<T extends AbstractEntity, RES>
        implements RequestHandler<Long, EntityModel<RES>> {

    private final RepresentationModelAssembler<T, EntityModel<RES>> assembler;
    private final ReadOperation<T> readOperation;

    @Override
    public EntityModel<RES> handle(Long id) {
        return assembler.toModel(readOperation.execute(id));
    }
}
