package com.vladislav.crm.controllers.requesthandlers.leads;

import com.vladislav.crm.controllers.requesthandlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.controllers.responses.ReadLeadResponse;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.ReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReadLeadRequestHandlerImpl
        extends AbstractReadEntityRequestHandler<Lead, ReadLeadResponse>
        implements ReadLeadRequestHandler {

    @Autowired
    public ReadLeadRequestHandlerImpl(
            RepresentationModelAssembler<Lead, EntityModel<ReadLeadResponse>> assembler,
            ReadOperation<Lead> readOperation
    ) {
        super(assembler, readOperation);
    }
}
