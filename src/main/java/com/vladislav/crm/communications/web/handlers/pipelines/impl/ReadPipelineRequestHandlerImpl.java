package com.vladislav.crm.communications.web.handlers.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.communications.web.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.web.handlers.pipelines.ReadPipelineRequestHandler;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReadPipelineRequestHandlerImpl
        extends AbstractReadEntityRequestHandler<Pipeline, ReadPipelineResponse>
        implements ReadPipelineRequestHandler {

    @Autowired
    public ReadPipelineRequestHandlerImpl(
            RepresentationModelAssembler<Pipeline, EntityModel<ReadPipelineResponse>> assembler,
            ReadOperation<Pipeline> readPipelineOperation
    ) {
        super(assembler, readPipelineOperation);
    }
}
