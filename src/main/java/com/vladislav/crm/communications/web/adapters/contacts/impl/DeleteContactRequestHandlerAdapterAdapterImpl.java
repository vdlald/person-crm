package com.vladislav.crm.communications.web.adapters.contacts.impl;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.contacts.DeleteContactRequestHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteContactRequestHandlerAdapterAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Contact>
        implements DeleteContactRequestHandlerAdapter {

    @Autowired
    public DeleteContactRequestHandlerAdapterAdapterImpl(DeleteOperation<Contact> deleteOperation) {
        super(deleteOperation);
    }
}
