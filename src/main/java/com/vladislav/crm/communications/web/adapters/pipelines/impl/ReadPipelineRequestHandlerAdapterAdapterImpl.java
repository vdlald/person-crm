package com.vladislav.crm.communications.web.adapters.pipelines.impl;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractReadEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.pipelines.ReadPipelineRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import com.vladislav.crm.entities.Pipeline;
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
            AbstractReadEntityRequestHandler<Pipeline> requestHandler
    ) {
        super(assembler, requestHandler);
    }
}
