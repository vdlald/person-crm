package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.User;

public interface CreateUserOperation {
    User execute(User newUser);
}
