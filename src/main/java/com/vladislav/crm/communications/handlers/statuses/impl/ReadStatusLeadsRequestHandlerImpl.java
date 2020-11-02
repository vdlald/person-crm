package com.vladislav.crm.communications.handlers.statuses.impl;

import com.vladislav.crm.communications.handlers.statuses.ReadStatusLeadsRequestHandler;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.leads.ReadStatusLeadsOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadStatusLeadsRequestHandlerImpl implements ReadStatusLeadsRequestHandler {

    private final ReadStatusLeadsOperation readStatusLeadsOperation;

    @Override
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#statusId) || " +
            "@userOwnsReadAllAuthorization.hasAuthorization()")
    public Collection<Lead> handle(Long statusId) {
        return readStatusLeadsOperation.execute(statusId);
    }
}
