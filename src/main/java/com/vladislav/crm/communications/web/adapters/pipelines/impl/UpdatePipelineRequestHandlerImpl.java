package com.vladislav.crm.communications.web.adapters.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import com.vladislav.crm.communications.web.assemblers.ReadPipelineResponseAssembler;
import com.vladislav.crm.communications.web.adapters.pipelines.UpdatePipelineRequestHandler;
import com.vladislav.crm.communications.web.requests.UpdatePipelineRequest;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdatePipelineRequestHandlerImpl implements UpdatePipelineRequestHandler {

    private final ReadOperation<Pipeline> readPipelineOperation;
    private final UpdateOperation<Pipeline> pipelineUpdateOperation;
    private final ReadPipelineResponseAssembler readPipelineResponseAssembler;

    @Override
    public EntityModel<ReadPipelineResponse> handle(Pair<Long, UpdatePipelineRequest> requestPair) {
        final Long pipelineId = requestPair.getFirst();
        final UpdatePipelineRequest request = requestPair.getSecond();

        final Pipeline pipeline = readPipelineOperation.execute(pipelineId)
                .setName(request.getName());

        return readPipelineResponseAssembler.toModel(pipelineUpdateOperation.execute(pipeline));
    }
}
