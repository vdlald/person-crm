package com.vladislav.crm.communications.web.handlers.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.communications.web.assemblers.ReadPipelineResponseAssembler;
import com.vladislav.crm.communications.web.handlers.pipelines.CreatePipelineRequestHandler;
import com.vladislav.crm.communications.web.requests.CreatePipelineRequest;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreatePipelineRequestHandlerImpl implements CreatePipelineRequestHandler {

    private final CreateOperation<Pipeline> createPipelineOperation;
    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final ReadPipelineResponseAssembler readPipelineResponseAssembler;

    @Override
    public EntityModel<ReadPipelineResponse> handle(CreatePipelineRequest request) {
        final Pipeline pipeline = new Pipeline()
                .setName(request.getName())
                .setUserUnsafe(getCurrentUserStubOperation.execute());

        return readPipelineResponseAssembler.toModel(createPipelineOperation.execute(pipeline));
    }
}
