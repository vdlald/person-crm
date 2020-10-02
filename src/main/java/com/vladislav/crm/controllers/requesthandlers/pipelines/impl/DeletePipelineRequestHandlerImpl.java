package com.vladislav.crm.controllers.requesthandlers.pipelines.impl;

import com.vladislav.crm.controllers.requesthandlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.pipelines.DeletePipelineRequestHandler;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.DeleteOperation;
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
