package com.vladislav.crm.services.operations.users;

import com.vladislav.crm.entities.User;

public interface GetCurrentUserPrincipalOperation {
    User execute();
}
