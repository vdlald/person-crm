package com.vladislav.crm.communications.web.adapters.statuses.impl;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.statuses.DeleteStatusRequestHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteStatusRequestHandlerAdapterAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Status>
        implements DeleteStatusRequestHandlerAdapter {

    @Autowired
    public DeleteStatusRequestHandlerAdapterAdapterImpl(DeleteOperation<Status> deleteOperation) {
        super(deleteOperation);
    }
}
