package com.vladislav.crm.communications.web.adapters.contacts.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.contacts.DeleteContactRequestHandlerAdapter;
import com.vladislav.crm.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("webDeleteContactRequestHandlerAdapter")
public class DeleteContactRequestHandlerAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Contact>
        implements DeleteContactRequestHandlerAdapter {

    @Autowired
    public DeleteContactRequestHandlerAdapterImpl(AbstractDeleteEntityRequestHandler<Contact> requestHandler) {
        super(requestHandler);
    }
}
