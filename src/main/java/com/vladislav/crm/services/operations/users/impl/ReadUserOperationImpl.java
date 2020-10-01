package com.vladislav.crm.services.operations.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.abstractions.AbstractReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReadUserOperationImpl extends AbstractReadOperation<User> {
    @Autowired
    public ReadUserOperationImpl(JpaRepository<User, Long> repository) {
        super(repository);
    }
}
