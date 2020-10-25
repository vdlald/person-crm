package com.vladislav.crm.web.handlers.statuses.impl;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.web.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.web.handlers.statuses.ReadStatusRequestHandler;
import com.vladislav.crm.web.responses.ReadStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReadStatusRequestHandlerImpl
        extends AbstractReadEntityRequestHandler<Status, ReadStatusResponse>
        implements ReadStatusRequestHandler {

    @Autowired
    public ReadStatusRequestHandlerImpl(
            RepresentationModelAssembler<Status, EntityModel<ReadStatusResponse>> assembler,
            ReadOperation<Status> readStatusOperation
    ) {
        super(assembler, readStatusOperation);
    }
}
