package com.vladislav.crm.services.operations.statuses.impl;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.abstractions.AbstractUpdateOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateStatusOperationImpl extends AbstractUpdateOperation<Status> {

    @Autowired
    public UpdateStatusOperationImpl(JpaRepository<Status, Long> repository) {
        super(repository);
    }
}
