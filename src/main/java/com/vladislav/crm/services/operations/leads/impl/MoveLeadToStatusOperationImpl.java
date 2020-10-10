package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.repositories.LeadRepository;
import com.vladislav.crm.services.operations.leads.MoveLeadToStatusOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoveLeadToStatusOperationImpl implements MoveLeadToStatusOperation {

    private final LeadRepository leadRepository;

    @Override
    public void execute(Long leadId, Long statusId) {
        leadRepository.moveLeadToStatus(leadId, statusId);
    }
}
