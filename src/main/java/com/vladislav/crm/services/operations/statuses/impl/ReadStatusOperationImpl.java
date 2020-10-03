package com.vladislav.crm.services.operations.statuses.impl;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.abstractions.AbstractReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReadStatusOperationImpl extends AbstractReadOperation<Status> {

    @Autowired
    public ReadStatusOperationImpl(JpaRepository<Status, Long> repository) {
        super(repository);
    }
}
