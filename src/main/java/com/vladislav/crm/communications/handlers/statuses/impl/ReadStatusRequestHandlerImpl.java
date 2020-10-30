package com.vladislav.crm.communications.handlers.statuses.impl;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.handlers.statuses.ReadStatusRequestHandler;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.ReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadStatusRequestHandlerImpl
        extends AbstractReadEntityRequestHandler<Status>
        implements ReadStatusRequestHandler {

    @Autowired
    public ReadStatusRequestHandlerImpl(
            ReadOperation<Status> readStatusOperation
    ) {
        super(readStatusOperation);
    }
}
