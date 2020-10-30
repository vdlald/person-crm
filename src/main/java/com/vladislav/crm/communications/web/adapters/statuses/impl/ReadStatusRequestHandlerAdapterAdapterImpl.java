package com.vladislav.crm.communications.web.adapters.statuses.impl;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.communications.web.adapters.AbstractReadEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.statuses.ReadStatusRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReadStatusRequestHandlerAdapterAdapterImpl
        extends AbstractReadEntityRequestHandlerAdapter<Status, ReadStatusResponse>
        implements ReadStatusRequestHandlerAdapter {

    @Autowired
    public ReadStatusRequestHandlerAdapterAdapterImpl(
            RepresentationModelAssembler<Status, EntityModel<ReadStatusResponse>> assembler,
            ReadOperation<Status> readStatusOperation
    ) {
        super(assembler, readStatusOperation);
    }
}
