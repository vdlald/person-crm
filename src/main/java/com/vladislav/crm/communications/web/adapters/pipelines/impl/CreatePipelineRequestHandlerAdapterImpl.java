package com.vladislav.crm.communications.web.adapters.pipelines.impl;

import com.vladislav.crm.communications.handlers.pipelines.CreatePipelineRequestHandler;
import com.vladislav.crm.communications.web.adapters.pipelines.CreatePipelineRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadPipelineResponseAssembler;
import com.vladislav.crm.communications.web.requests.CreatePipelineRequest;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import com.vladislav.crm.entities.Pipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webCreatePipelineRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreatePipelineRequestHandlerAdapterImpl implements CreatePipelineRequestHandlerAdapter {

    private final CreatePipelineRequestHandler requestHandler;
    private final ReadPipelineResponseAssembler readPipelineResponseAssembler;

    @Override
    public EntityModel<ReadPipelineResponse> handle(CreatePipelineRequest request) {
        final Pipeline pipeline = requestHandler.handle(request.toCommunicationRequest());
        return readPipelineResponseAssembler.toModel(pipeline);
    }
}
