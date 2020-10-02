package com.vladislav.crm.controllers.requesthandlers.pipelines.impl;

import com.vladislav.crm.controllers.requesthandlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.pipelines.ReadPipelineRequestHandler;
import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.ReadOperation;
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
            ReadOperation<Pipeline> readOperation
    ) {
        super(assembler, readOperation);
    }
}
