package com.vladislav.crm.controllers.requesthandlers.pipelines.impl;

import com.vladislav.crm.controllers.assemblers.ReadPipelineResponseAssembler;
import com.vladislav.crm.controllers.requesthandlers.pipelines.UpdatePipelineRequestHandler;
import com.vladislav.crm.controllers.requests.UpdatePipelineRequest;
import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdatePipelineRequestHandlerImpl implements UpdatePipelineRequestHandler {

    private final ReadOperation<Pipeline> pipelineReadOperation;
    private final UpdateOperation<Pipeline> pipelineUpdateOperation;
    private final ReadPipelineResponseAssembler readPipelineResponseAssembler;

    @Override
    public EntityModel<ReadPipelineResponse> handle(Pair<Long, UpdatePipelineRequest> requestPair) {
        final Long pipelineId = requestPair.getFirst();
        final UpdatePipelineRequest request = requestPair.getSecond();

        final Pipeline pipeline = pipelineReadOperation.execute(pipelineId)
                .setName(request.getName());

        return readPipelineResponseAssembler.toModel(pipelineUpdateOperation.execute(pipeline));
    }
}
