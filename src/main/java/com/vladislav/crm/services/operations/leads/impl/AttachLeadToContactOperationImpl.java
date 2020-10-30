package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.repositories.LeadRepository;
import com.vladislav.crm.services.operations.leads.AttachLeadToContactOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachLeadToContactOperationImpl implements AttachLeadToContactOperation {

    private final LeadRepository leadRepository;

    @Override
    public void execute(
            @NonNull Long leadId,
            @NonNull Long contactId
    ) {
        leadRepository.attachLeadToContact(leadId, contactId);
    }
}
