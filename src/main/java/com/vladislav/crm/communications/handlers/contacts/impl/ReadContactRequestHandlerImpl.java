package com.vladislav.crm.communications.handlers.contacts.impl;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.handlers.contacts.ReadContactRequestHandler;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.ReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadContactRequestHandlerImpl
        extends AbstractReadEntityRequestHandler<Contact>
        implements ReadContactRequestHandler {

    @Autowired
    public ReadContactRequestHandlerImpl(
            ReadOperation<Contact> readOperation
    ) {
        super(readOperation);
    }
}
