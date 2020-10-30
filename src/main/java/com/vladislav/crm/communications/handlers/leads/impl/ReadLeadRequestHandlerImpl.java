package com.vladislav.crm.communications.handlers.leads.impl;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.handlers.leads.ReadLeadRequestHandler;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.ReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadLeadRequestHandlerImpl
        extends AbstractReadEntityRequestHandler<Lead>
        implements ReadLeadRequestHandler {

    @Autowired
    public ReadLeadRequestHandlerImpl(
            ReadOperation<Lead> readOperation
    ) {
        super(readOperation);
    }
}
