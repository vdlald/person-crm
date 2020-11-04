package com.vladislav.crm.communications.handlers.contacts.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.handlers.contacts.DeleteContactRequestHandler;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.DeleteOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DeleteContactRequestHandlerImpl
        extends AbstractDeleteEntityRequestHandler<Contact>
        implements DeleteContactRequestHandler {

    @Autowired
    public DeleteContactRequestHandlerImpl(DeleteOperation<Contact> deleteOperation) {
        super(deleteOperation);
    }

    @Override
    @PreAuthorize("@userOwnsContactAuthorization.hasAuthorization(#id)")
    public Void handle(Long id) {
        return super.handle(id);
    }
}
