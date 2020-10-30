package com.vladislav.crm.communications.web.handlers.leads.impl;

import com.vladislav.crm.services.operations.leads.MoveLeadToStatusOperation;
import com.vladislav.crm.communications.web.handlers.leads.MoveLeadToAnotherStatusRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoveLeadToAnotherStatusRequestHandlerImpl implements MoveLeadToAnotherStatusRequestHandler {

    private final MoveLeadToStatusOperation moveLeadToStatusOperation;

    @Override
    public ResponseEntity<Void> handle(Pair<Long, Long> requestPair) {
        final Long leadId = requestPair.getFirst();
        final Long statusId = requestPair.getSecond();

        moveLeadToStatusOperation.execute(leadId, statusId);

        return ResponseEntity.noContent().build();
    }
}
