package com.vladislav.crm.controllers.requesthandlers.pipelines.impl;

import com.vladislav.crm.controllers.assemblers.ReadPipelineResponseAssembler;
import com.vladislav.crm.controllers.requesthandlers.pipelines.CreatePipelineRequestHandler;
import com.vladislav.crm.controllers.requests.CreatePipelineRequest;
import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.pipelines.CreatePipelineOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreatePipelineRequestHandlerImpl implements CreatePipelineRequestHandler {

    private final CreatePipelineOperation createPipelineOperation;
    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
    private final ReadPipelineResponseAssembler readPipelineResponseAssembler;

    @Override
    public EntityModel<ReadPipelineResponse> handle(CreatePipelineRequest request) {
        final Pipeline pipeline = new Pipeline()
                .setName(request.getName())
                .setUser(getCurrentUserStubOperation.execute());

        return readPipelineResponseAssembler.toModel(createPipelineOperation.execute(pipeline));
    }
}
