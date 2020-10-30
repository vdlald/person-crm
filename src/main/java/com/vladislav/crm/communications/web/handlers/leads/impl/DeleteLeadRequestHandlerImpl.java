package com.vladislav.crm.communications.web.handlers.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.communications.web.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.web.handlers.leads.DeleteLeadRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteLeadRequestHandlerImpl
        extends AbstractDeleteEntityRequestHandler<Lead>
        implements DeleteLeadRequestHandler {

    @Autowired
    public DeleteLeadRequestHandlerImpl(DeleteOperation<Lead> deleteOperation) {
        super(deleteOperation);
    }
}
