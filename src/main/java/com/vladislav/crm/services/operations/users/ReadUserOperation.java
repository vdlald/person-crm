package com.vladislav.crm.services.operations.users;

import com.vladislav.crm.entities.User;

public interface ReadUserOperation {
    User execute(Long id);
}
