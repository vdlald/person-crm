package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.leads.DeleteLeadRequestHandlerAdapter;
import com.vladislav.crm.entities.Lead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteLeadRequestHandlerAdapterAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Lead>
        implements DeleteLeadRequestHandlerAdapter {

    @Autowired
    public DeleteLeadRequestHandlerAdapterAdapterImpl(AbstractDeleteEntityRequestHandler<Lead> requestHandler) {
        super(requestHandler);
    }
}
