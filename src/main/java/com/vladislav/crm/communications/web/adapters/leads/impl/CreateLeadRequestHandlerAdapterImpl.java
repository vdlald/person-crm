package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.communications.web.assemblers.ReadLeadResponseAssembler;
import com.vladislav.crm.communications.web.adapters.leads.CreateLeadRequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.CreateLeadRequest;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateLeadRequestHandlerAdapterImpl implements CreateLeadRequestHandlerAdapter {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final CreateOperation<Lead> leadCreateOperation;
    private final ReadLeadResponseAssembler readLeadResponseAssembler;
    private final ReadOperation<Status> readStatusStubOperation;

    @Override
    public EntityModel<ReadLeadResponse> handle(CreateLeadRequest request) {
        final Lead lead = new Lead()
                .setName(request.getName())
                .setSale(request.getSale())
                .setStatusUnsafe(readStatusStubOperation.execute(request.getStatusId()))
                .setUserUnsafe(getCurrentUserStubOperation.execute());

        return readLeadResponseAssembler.toModel(leadCreateOperation.execute(lead));
    }
}
