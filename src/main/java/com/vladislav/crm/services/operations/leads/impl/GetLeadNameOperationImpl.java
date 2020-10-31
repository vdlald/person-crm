package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.repositories.LeadRepository;
import com.vladislav.crm.services.operations.leads.GetLeadNameOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetLeadNameOperationImpl implements GetLeadNameOperation {

    private final LeadRepository leadRepository;

    @Override
    public String execute(Long id) {
        return leadRepository.findLeadNameById(id).orElseThrow(EntityNotFoundException::new);
    }
}
