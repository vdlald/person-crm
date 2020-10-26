package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.repositories.LeadRepository;
import com.vladislav.crm.services.operations.leads.GetStatusIdByLeadIdOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetStatusIdByLeadIdOperationImpl implements GetStatusIdByLeadIdOperation {

    private final LeadRepository leadRepository;

    @Override
    public Long execute(Long leadId) {
        return leadRepository.findStatusIdById(leadId).orElseThrow(EntityNotFoundException::new);
    }
}
