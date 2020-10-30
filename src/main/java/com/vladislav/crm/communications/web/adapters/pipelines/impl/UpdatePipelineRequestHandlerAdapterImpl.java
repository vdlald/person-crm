package com.vladislav.crm.communications.web.adapters.pipelines.impl;

import com.vladislav.crm.communications.handlers.pipelines.UpdatePipelineRequestHandler;
import com.vladislav.crm.communications.web.adapters.pipelines.UpdatePipelineRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadPipelineResponseAssembler;
import com.vladislav.crm.communications.web.requests.UpdatePipelineRequest;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdatePipelineRequestHandlerAdapterImpl implements UpdatePipelineRequestHandlerAdapter {

    private final UpdatePipelineRequestHandler requestHandler;
    private final ReadPipelineResponseAssembler readPipelineResponseAssembler;

    @Override
    public EntityModel<ReadPipelineResponse> handle(Pair<Long, UpdatePipelineRequest> requestPair) {
        final Long pipelineId = requestPair.getFirst();
        final UpdatePipelineRequest request = requestPair.getSecond();
        return readPipelineResponseAssembler.toModel(
                requestHandler.handle(Pair.of(pipelineId, request.toCommunicationRequest())));
    }
}
