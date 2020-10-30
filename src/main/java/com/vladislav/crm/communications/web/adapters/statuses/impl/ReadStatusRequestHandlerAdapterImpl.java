package com.vladislav.crm.communications.web.adapters.statuses.impl;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractReadEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.statuses.ReadStatusRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import com.vladislav.crm.entities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component("webReadStatusRequestHandlerAdapter")
public class ReadStatusRequestHandlerAdapterImpl
        extends AbstractReadEntityRequestHandlerAdapter<Status, ReadStatusResponse>
        implements ReadStatusRequestHandlerAdapter {

    @Autowired
    public ReadStatusRequestHandlerAdapterImpl(
            RepresentationModelAssembler<Status, EntityModel<ReadStatusResponse>> assembler,
            AbstractReadEntityRequestHandler<Status> requestHandler
    ) {
        super(assembler, requestHandler);
    }
}
