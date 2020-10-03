package com.vladislav.crm.web.requesthandlers.leads.impl;

import com.vladislav.crm.web.requesthandlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.web.requesthandlers.leads.ReadLeadRequestHandler;
import com.vladislav.crm.web.responses.ReadLeadResponse;
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
