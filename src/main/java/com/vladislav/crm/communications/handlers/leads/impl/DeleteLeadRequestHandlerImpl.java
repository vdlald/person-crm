package com.vladislav.crm.communications.handlers.leads.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.handlers.leads.DeleteLeadRequestHandler;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.DeleteOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DeleteLeadRequestHandlerImpl
        extends AbstractDeleteEntityRequestHandler<Lead>
        implements DeleteLeadRequestHandler {

    @Autowired
    public DeleteLeadRequestHandlerImpl(DeleteOperation<Lead> deleteOperation) {
        super(deleteOperation);
    }

    @Override
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#id)")
    public Void handle(Long id) {
        return super.handle(id);
    }
}
