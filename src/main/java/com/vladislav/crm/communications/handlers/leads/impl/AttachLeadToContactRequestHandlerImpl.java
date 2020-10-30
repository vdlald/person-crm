package com.vladislav.crm.communications.handlers.leads.impl;

import com.vladislav.crm.communications.handlers.leads.AttachLeadToContactRequestHandler;
import com.vladislav.crm.services.operations.leads.AttachLeadToContactOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachLeadToContactRequestHandlerImpl implements AttachLeadToContactRequestHandler {

    private final AttachLeadToContactOperation attachLeadToContactOperation;

    @Override
    public Void handle(Pair<Long, Long> requestPair) {
        final Long leadId = requestPair.getFirst();
        final Long contactId = requestPair.getSecond();

        attachLeadToContactOperation.execute(leadId, contactId);

        return null;
    }
}
