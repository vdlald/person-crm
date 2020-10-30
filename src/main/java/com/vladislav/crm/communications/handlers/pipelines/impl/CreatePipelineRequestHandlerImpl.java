package com.vladislav.crm.communications.handlers.pipelines.impl;

import com.vladislav.crm.communications.handlers.pipelines.CreatePipelineRequestHandler;
import com.vladislav.crm.communications.requests.CreatePipelineRequest;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreatePipelineRequestHandlerImpl implements CreatePipelineRequestHandler {

    private final CreateOperation<Pipeline> createPipelineOperation;
    private final GetCurrentUserOperation getCurrentUserStubOperation;

    @Override
    public Pipeline handle(CreatePipelineRequest request) {
        final Pipeline pipeline = new Pipeline()
                .setName(request.getName())
                .setUserUnsafe(getCurrentUserStubOperation.execute());

        return createPipelineOperation.execute(pipeline);
    }
}
