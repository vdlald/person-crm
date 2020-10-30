package com.vladislav.crm.communications.web.handlers.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.communications.web.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.web.handlers.pipelines.DeletePipelineRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePipelineRequestHandlerImpl
        extends AbstractDeleteEntityRequestHandler<Pipeline>
        implements DeletePipelineRequestHandler {

    @Autowired
    public DeletePipelineRequestHandlerImpl(DeleteOperation<Pipeline> deleteOperation) {
        super(deleteOperation);
    }
}
