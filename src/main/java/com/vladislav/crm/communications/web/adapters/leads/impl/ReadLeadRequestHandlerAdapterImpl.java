package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractReadEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.leads.ReadLeadRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import com.vladislav.crm.entities.Lead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component("webReadLeadRequestHandlerAdapter")
public class ReadLeadRequestHandlerAdapterImpl
        extends AbstractReadEntityRequestHandlerAdapter<Lead, ReadLeadResponse>
        implements ReadLeadRequestHandlerAdapter {

    @Autowired
    public ReadLeadRequestHandlerAdapterImpl(
            RepresentationModelAssembler<Lead, EntityModel<ReadLeadResponse>> assembler,
            AbstractReadEntityRequestHandler<Lead> requestHandler
    ) {
        super(assembler, requestHandler);
    }
}
