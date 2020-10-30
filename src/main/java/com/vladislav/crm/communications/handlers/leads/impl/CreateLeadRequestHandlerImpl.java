package com.vladislav.crm.communications.handlers.leads.impl;

import com.vladislav.crm.communications.handlers.leads.CreateLeadRequestHandler;
import com.vladislav.crm.communications.requests.CreateLeadRequest;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateLeadRequestHandlerImpl implements CreateLeadRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final CreateOperation<Lead> leadCreateOperation;
    private final ReadOperation<Status> readStatusStubOperation;

    @Override
    public Lead handle(CreateLeadRequest request) {
        final Lead lead = new Lead()
                .setName(request.getName())
                .setSale(request.getSale())
                .setStatusUnsafe(readStatusStubOperation.execute(request.getStatusId()))
                .setUserUnsafe(getCurrentUserStubOperation.execute());

        return leadCreateOperation.execute(lead);
    }
}
