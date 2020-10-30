package com.vladislav.crm.communications.handlers.leads.impl;

import com.vladislav.crm.communications.handlers.leads.UpdateLeadRequestHandler;
import com.vladislav.crm.communications.requests.UpdateLeadRequest;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateLeadRequestHandlerImpl implements UpdateLeadRequestHandler {

    private final ReadOperation<Lead> leadReadOperation;
    private final UpdateOperation<Lead> leadUpdateOperation;

    @Override
    public Lead handle(Pair<Long, UpdateLeadRequest> requestPair) {
        final Long id = requestPair.getFirst();
        final UpdateLeadRequest request = requestPair.getSecond();

        final Lead lead = leadReadOperation.execute(id);
        lead.setName(request.getName())
                .setSale(request.getSale());

        return leadUpdateOperation.execute(lead);
    }
}
