package com.vladislav.crm.communications.handlers.pipelines.impl;

import com.vladislav.crm.communications.handlers.pipelines.UpdatePipelineRequestHandler;
import com.vladislav.crm.communications.requests.UpdatePipelineRequest;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdatePipelineRequestHandlerImpl implements UpdatePipelineRequestHandler {

    private final ReadOperation<Pipeline> readPipelineOperation;
    private final UpdateOperation<Pipeline> pipelineUpdateOperation;

    @Override
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#requestPair.first)")
    public Pipeline handle(Pair<Long, UpdatePipelineRequest> requestPair) {
        final Long pipelineId = requestPair.getFirst();
        final UpdatePipelineRequest request = requestPair.getSecond();

        final Pipeline pipeline = readPipelineOperation.execute(pipelineId)
                .setName(request.getName());

        return pipelineUpdateOperation.execute(pipeline);
    }
}
