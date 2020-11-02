package com.vladislav.crm.communications.handlers.pipelines.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.handlers.pipelines.DeletePipelineRequestHandler;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.DeleteOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DeletePipelineRequestHandlerImpl
        extends AbstractDeleteEntityRequestHandler<Pipeline>
        implements DeletePipelineRequestHandler {

    @Autowired
    public DeletePipelineRequestHandlerImpl(DeleteOperation<Pipeline> deleteOperation) {
        super(deleteOperation);
    }

    @Override
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#id)")
    public Void handle(Long id) {
        return super.handle(id);
    }
}
