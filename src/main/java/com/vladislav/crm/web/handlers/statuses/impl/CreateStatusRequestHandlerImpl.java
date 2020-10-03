package com.vladislav.crm.web.handlers.statuses.impl;

import com.vladislav.crm.web.assemblers.ReadStatusResponseAssembler;
import com.vladislav.crm.web.handlers.statuses.CreateStatusRequestHandler;
import com.vladislav.crm.web.requests.CreateStatusRequest;
import com.vladislav.crm.web.responses.ReadStatusResponse;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateStatusRequestHandlerImpl implements CreateStatusRequestHandler {

    private final CreateOperation<Status> statusCreateOperation;
    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
    private final ReadStatusResponseAssembler readStatusResponseAssembler;

    @Override
    public EntityModel<ReadStatusResponse> handle(CreateStatusRequest request) {
        final Pipeline pipelineStub = new Pipeline();
        pipelineStub.setId(request.getPipelineId());

        final Status status = new Status()
                .setName(request.getName())
                .setPipeline(pipelineStub)
                .setUser(getCurrentUserStubOperation.execute());

        return readStatusResponseAssembler.toModel(statusCreateOperation.execute(status));
    }
}
