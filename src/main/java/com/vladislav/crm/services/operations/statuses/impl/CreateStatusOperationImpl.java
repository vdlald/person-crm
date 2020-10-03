package com.vladislav.crm.services.operations.statuses.impl;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.abstractions.AbstractCreateOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateStatusOperationImpl extends AbstractCreateOperation<Status> {

    @Autowired
    public CreateStatusOperationImpl(JpaRepository<Status, Long> repository) {
        super(repository);
    }
}
