package com.vladislav.crm.communications.web.handlers.contacts.impl;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.communications.web.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.web.handlers.contacts.ReadContactRequestHandler;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReadContactRequestHandlerImpl
        extends AbstractReadEntityRequestHandler<Contact, ReadContactResponse>
        implements ReadContactRequestHandler {

    @Autowired
    public ReadContactRequestHandlerImpl(
            RepresentationModelAssembler<Contact, EntityModel<ReadContactResponse>> assembler,
            ReadOperation<Contact> readOperation
    ) {
        super(assembler, readOperation);
    }
}
