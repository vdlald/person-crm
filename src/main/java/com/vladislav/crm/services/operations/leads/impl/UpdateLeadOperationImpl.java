package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.abstractions.AbstractUpdateOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateLeadOperationImpl extends AbstractUpdateOperation<Lead> {

    @Autowired
    public UpdateLeadOperationImpl(JpaRepository<Lead, Long> repository) {
        super(repository);
    }
}
