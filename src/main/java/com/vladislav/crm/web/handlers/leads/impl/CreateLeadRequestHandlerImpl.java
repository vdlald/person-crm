package com.vladislav.crm.web.handlers.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.web.assemblers.ReadLeadResponseAssembler;
import com.vladislav.crm.web.handlers.leads.CreateLeadRequestHandler;
import com.vladislav.crm.web.requests.CreateLeadRequest;
import com.vladislav.crm.web.responses.ReadLeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateLeadRequestHandlerImpl implements CreateLeadRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final CreateOperation<Lead> leadCreateOperation;
    private final ReadLeadResponseAssembler readLeadResponseAssembler;
    private final ReadOperation<Status> statusReadOperation;

    @Override
    public EntityModel<ReadLeadResponse> handle(CreateLeadRequest request) {
        final Lead lead = new Lead()
                .setName(request.getName())
                .setSale(request.getSale())
                .setStatus(statusReadOperation.execute(request.getStatusId()))
                .setUser(getCurrentUserStubOperation.execute());

        return readLeadResponseAssembler.toModel(leadCreateOperation.execute(lead));
    }
}
