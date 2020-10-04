package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.repositories.LeadRepository;
import com.vladislav.crm.services.operations.leads.ReadStatusLeadsOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadStatusLeadsOperationImpl implements ReadStatusLeadsOperation {

    private final LeadRepository leadRepository;

    @Override
    public Collection<Lead> execute(Long statusId) {
        return leadRepository.findAllByStatusId(statusId);
    }
}
