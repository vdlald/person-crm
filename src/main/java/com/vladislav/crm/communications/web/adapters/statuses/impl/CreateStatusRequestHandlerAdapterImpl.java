package com.vladislav.crm.communications.web.adapters.statuses.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.communications.web.assemblers.ReadStatusResponseAssembler;
import com.vladislav.crm.communications.web.adapters.statuses.CreateStatusRequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.CreateStatusRequest;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateStatusRequestHandlerAdapterImpl implements CreateStatusRequestHandlerAdapter {

    private final CreateOperation<Status> statusCreateOperation;
    private final ReadOperation<Pipeline> readPipelineOperation;
    private final ReadStatusResponseAssembler readStatusResponseAssembler;

    @Override
    public EntityModel<ReadStatusResponse> handle(CreateStatusRequest request) {
        final Status status = new Status()
                .setName(request.getName())
                .setPipelineUnsafe(readPipelineOperation.execute(request.getPipelineId()));

        return readStatusResponseAssembler.toModel(statusCreateOperation.execute(status));
    }
}
