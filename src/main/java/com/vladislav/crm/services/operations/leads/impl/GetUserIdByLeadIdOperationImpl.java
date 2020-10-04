package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.repositories.LeadRepository;
import com.vladislav.crm.services.operations.leads.GetUserIdByLeadIdOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserIdByLeadIdOperationImpl implements GetUserIdByLeadIdOperation {

    private final LeadRepository leadRepository;

    @Override
    public long execute(long leadId) {
        return leadRepository.findUserIdById(leadId).orElseThrow(EntityNotFoundException::new);
    }
}
