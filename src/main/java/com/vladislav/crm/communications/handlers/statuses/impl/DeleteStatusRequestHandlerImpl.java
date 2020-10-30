package com.vladislav.crm.communications.handlers.statuses.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.handlers.statuses.DeleteStatusRequestHandler;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.DeleteOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteStatusRequestHandlerImpl
        extends AbstractDeleteEntityRequestHandler<Status>
        implements DeleteStatusRequestHandler {

    @Autowired
    public DeleteStatusRequestHandlerImpl(DeleteOperation<Status> deleteOperation) {
        super(deleteOperation);
    }
}
