package com.vladislav.crm.controllers.requesthandlers.statuses;

import com.vladislav.crm.controllers.requesthandlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.ReadOperation;
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
            ReadOperation<Status> readOperation
    ) {
        super(assembler, readOperation);
    }
}
