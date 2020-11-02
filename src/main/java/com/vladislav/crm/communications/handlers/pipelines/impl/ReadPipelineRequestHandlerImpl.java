package com.vladislav.crm.communications.handlers.pipelines.impl;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.handlers.pipelines.ReadPipelineRequestHandler;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.ReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ReadPipelineRequestHandlerImpl
        extends AbstractReadEntityRequestHandler<Pipeline>
        implements ReadPipelineRequestHandler {

    @Autowired
    public ReadPipelineRequestHandlerImpl(
            ReadOperation<Pipeline> readPipelineOperation
    ) {
        super(readPipelineOperation);
    }

    @Override
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#id) || " +
            "@userOwnsReadAllAuthorization.hasAuthorization()")
    public Pipeline handle(Long id) {
        return super.handle(id);
    }
}
