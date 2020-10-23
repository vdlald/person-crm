package com.vladislav.crm.services.operations.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.abstractions.AbstractUpdateOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserOperationImpl extends AbstractUpdateOperation<User> {
    public UpdateUserOperationImpl(JpaRepository<User, Long> repository) {
        super(repository);
    }
}
