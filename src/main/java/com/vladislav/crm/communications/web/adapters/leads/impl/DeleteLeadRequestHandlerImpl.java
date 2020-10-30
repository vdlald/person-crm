package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.leads.DeleteLeadRequestHandler;
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
