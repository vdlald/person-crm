package com.vladislav.crm.controllers.requesthandlers.contacts.impl;

import com.vladislav.crm.controllers.requesthandlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.contacts.DeleteContactRequestHandler;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.DeleteOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteContactRequestHandlerImpl
        extends AbstractDeleteEntityRequestHandler<Contact>
        implements DeleteContactRequestHandler {

    @Autowired
    public DeleteContactRequestHandlerImpl(DeleteOperation<Contact> deleteOperation) {
        super(deleteOperation);
    }
}
