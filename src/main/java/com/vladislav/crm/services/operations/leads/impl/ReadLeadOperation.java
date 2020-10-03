package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.abstractions.AbstractReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReadLeadOperation extends AbstractReadOperation<Lead> {

    @Autowired
    public ReadLeadOperation(JpaRepository<Lead, Long> repository) {
        super(repository);
    }
}
