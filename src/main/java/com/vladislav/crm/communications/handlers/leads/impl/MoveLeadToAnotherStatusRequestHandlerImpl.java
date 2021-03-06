package com.vladislav.crm.communications.handlers.leads.impl;

import com.vladislav.crm.communications.handlers.leads.MoveLeadToAnotherStatusRequestHandler;
import com.vladislav.crm.services.operations.leads.MoveLeadToStatusOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoveLeadToAnotherStatusRequestHandlerImpl implements MoveLeadToAnotherStatusRequestHandler {

    private final MoveLeadToStatusOperation moveLeadToStatusOperation;

    @Override
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#requestPair.first) && " +
            "@userOwnsStatusAuthorization.hasAuthorization(#requestPair.second)")
    public Void handle(Pair<Long, Long> requestPair) {
        final Long leadId = requestPair.getFirst();
        final Long statusId = requestPair.getSecond();

        moveLeadToStatusOperation.execute(leadId, statusId);

        return null;
    }
}
