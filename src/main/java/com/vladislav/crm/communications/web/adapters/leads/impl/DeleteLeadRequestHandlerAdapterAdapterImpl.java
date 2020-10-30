package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.leads.DeleteLeadRequestHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteLeadRequestHandlerAdapterAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Lead>
        implements DeleteLeadRequestHandlerAdapter {

    @Autowired
    public DeleteLeadRequestHandlerAdapterAdapterImpl(DeleteOperation<Lead> deleteOperation) {
        super(deleteOperation);
    }
}
