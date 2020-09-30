package com.vladislav.crm.controllers.requesthandlers.pipelines.impl;

import com.vladislav.crm.controllers.assemblers.ReadPipelineResponseAssembler;
import com.vladislav.crm.controllers.requesthandlers.pipelines.ReadPipelineRequestHandler;
import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import com.vladislav.crm.services.operations.pipelines.ReadPipelineOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadPipelineRequestHandlerImpl implements ReadPipelineRequestHandler {

    private final ReadPipelineResponseAssembler readPipelineResponseAssembler;
    private final ReadPipelineOperation readPipelineOperation;

    @Override
    public EntityModel<ReadPipelineResponse> handle(Long pipelineId) {
        return readPipelineResponseAssembler.toModel(readPipelineOperation.execute(pipelineId));
    }
}
