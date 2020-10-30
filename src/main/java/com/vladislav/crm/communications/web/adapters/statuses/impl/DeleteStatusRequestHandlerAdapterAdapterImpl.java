package com.vladislav.crm.communications.web.adapters.statuses.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.statuses.DeleteStatusRequestHandlerAdapter;
import com.vladislav.crm.entities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteStatusRequestHandlerAdapterAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Status>
        implements DeleteStatusRequestHandlerAdapter {

    @Autowired
    public DeleteStatusRequestHandlerAdapterAdapterImpl(AbstractDeleteEntityRequestHandler<Status> requestHandler) {
        super(requestHandler);
    }
}
