package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.abstractions.AbstractCreateOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateLeadOperationImpl extends AbstractCreateOperation<Lead> {

    @Autowired
    public CreateLeadOperationImpl(JpaRepository<Lead, Long> repository) {
        super(repository);
    }

    @Override  // refactor candidate: данный override нужен только для работы аспектов
    public Lead execute(Lead entity) {
        return super.execute(entity);
    }
}
