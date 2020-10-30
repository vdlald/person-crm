package com.vladislav.crm.communications.web.handlers.leads.impl;

import com.vladislav.crm.services.operations.leads.AttachLeadToContactOperation;
import com.vladislav.crm.communications.web.handlers.leads.AttachLeadToContactRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachLeadToContactRequestHandlerImpl implements AttachLeadToContactRequestHandler {

    private final AttachLeadToContactOperation attachLeadToContactOperation;

    @Override
    public ResponseEntity<Void> handle(Pair<Long, Long> requestPair) {
        final Long leadId = requestPair.getFirst();
        final Long contactId = requestPair.getSecond();

        attachLeadToContactOperation.execute(leadId, contactId);

        return ResponseEntity.noContent().build();
    }
}
