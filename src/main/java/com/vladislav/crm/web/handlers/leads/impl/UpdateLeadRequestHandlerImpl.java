package com.vladislav.crm.web.handlers.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import com.vladislav.crm.web.assemblers.ReadLeadResponseAssembler;
import com.vladislav.crm.web.handlers.leads.UpdateLeadRequestHandler;
import com.vladislav.crm.web.requests.UpdateLeadRequest;
import com.vladislav.crm.web.responses.ReadLeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateLeadRequestHandlerImpl implements UpdateLeadRequestHandler {

    private final ReadOperation<Lead> leadReadOperation;
    private final ReadOperation<Status> statusReadOperation;
    private final UpdateOperation<Lead> leadUpdateOperation;
    private final ReadLeadResponseAssembler readLeadResponseAssembler;

    @Override
    public EntityModel<ReadLeadResponse> handle(Pair<Long, UpdateLeadRequest> requestPair) {
        final Long id = requestPair.getFirst();
        final UpdateLeadRequest request = requestPair.getSecond();

        final Lead lead = leadReadOperation.execute(id);
        lead.setName(request.getName())
                .setSale(request.getSale());

        final Long statusId = request.getStatusId();
        if (statusId != null) {
            lead.setStatus(statusReadOperation.execute(statusId));
        }

        return readLeadResponseAssembler.toModel(leadUpdateOperation.execute(lead));
    }
}
