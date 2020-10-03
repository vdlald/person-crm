package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.abstractions.AbstractReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReadLeadOperationImpl extends AbstractReadOperation<Lead> {

    @Autowired
    public ReadLeadOperationImpl(JpaRepository<Lead, Long> repository) {
        super(repository);
    }
}
