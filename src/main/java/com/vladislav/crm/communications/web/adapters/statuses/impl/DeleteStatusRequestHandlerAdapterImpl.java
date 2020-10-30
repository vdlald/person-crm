package com.vladislav.crm.communications.web.adapters.statuses.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.statuses.DeleteStatusRequestHandlerAdapter;
import com.vladislav.crm.entities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("webDeleteStatusRequestHandlerAdapter")
public class DeleteStatusRequestHandlerAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Status>
        implements DeleteStatusRequestHandlerAdapter {

    @Autowired
    public DeleteStatusRequestHandlerAdapterImpl(AbstractDeleteEntityRequestHandler<Status> requestHandler) {
        super(requestHandler);
    }
}
