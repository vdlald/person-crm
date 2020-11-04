package com.vladislav.crm.communications.handlers.leads.impl;

import com.vladislav.crm.communications.handlers.leads.ReadLeadInfoRequestHandler;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.leads.ReadLeadInfoOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadLeadInfoRequestHandlerImpl implements ReadLeadInfoRequestHandler {

    private final ReadLeadInfoOperation readLeadInfoOperation;

    @Override
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#id) ||" +
            "@userOwnsReadAllAuthorization.hasAuthorization()")
    public Lead.LeadInfo handle(Long id) {
        return readLeadInfoOperation.execute(id);
    }
}
