package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.communications.web.adapters.AbstractReadEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.leads.ReadLeadRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReadLeadRequestHandlerAdapterAdapterImpl
        extends AbstractReadEntityRequestHandlerAdapter<Lead, ReadLeadResponse>
        implements ReadLeadRequestHandlerAdapter {

    @Autowired
    public ReadLeadRequestHandlerAdapterAdapterImpl(
            RepresentationModelAssembler<Lead, EntityModel<ReadLeadResponse>> assembler,
            ReadOperation<Lead> readOperation
    ) {
        super(assembler, readOperation);
    }
}
