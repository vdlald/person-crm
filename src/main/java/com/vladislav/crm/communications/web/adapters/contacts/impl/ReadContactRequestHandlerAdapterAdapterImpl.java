package com.vladislav.crm.communications.web.adapters.contacts.impl;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractReadEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.contacts.ReadContactRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import com.vladislav.crm.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReadContactRequestHandlerAdapterAdapterImpl
        extends AbstractReadEntityRequestHandlerAdapter<Contact, ReadContactResponse>
        implements ReadContactRequestHandlerAdapter {

    @Autowired
    public ReadContactRequestHandlerAdapterAdapterImpl(
            RepresentationModelAssembler<Contact, EntityModel<ReadContactResponse>> assembler,
            AbstractReadEntityRequestHandler<Contact> requestHandler
    ) {
        super(assembler, requestHandler);
    }
}
