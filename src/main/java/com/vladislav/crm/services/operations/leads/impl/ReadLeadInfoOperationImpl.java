package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.repositories.LeadRepository;
import com.vladislav.crm.services.operations.leads.ReadLeadInfoOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadLeadInfoOperationImpl implements ReadLeadInfoOperation {

    private final LeadRepository repository;

    @Override
    public Lead.LeadInfo execute(@NonNull Long id) {
        return repository.findLeadInfoById(id).orElseThrow(EntityNotFoundException::new);
    }
}
