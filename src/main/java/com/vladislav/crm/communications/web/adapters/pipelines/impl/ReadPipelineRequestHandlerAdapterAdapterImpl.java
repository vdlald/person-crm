package com.vladislav.crm.communications.web.adapters.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.communications.web.adapters.AbstractReadEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.pipelines.ReadPipelineRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReadPipelineRequestHandlerAdapterAdapterImpl
        extends AbstractReadEntityRequestHandlerAdapter<Pipeline, ReadPipelineResponse>
        implements ReadPipelineRequestHandlerAdapter {

    @Autowired
    public ReadPipelineRequestHandlerAdapterAdapterImpl(
            RepresentationModelAssembler<Pipeline, EntityModel<ReadPipelineResponse>> assembler,
            ReadOperation<Pipeline> readPipelineOperation
    ) {
        super(assembler, readPipelineOperation);
    }
}
