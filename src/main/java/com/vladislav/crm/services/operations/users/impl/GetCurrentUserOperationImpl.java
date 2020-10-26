package com.vladislav.crm.services.operations.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.GetCurrentUserPrincipalOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("getCurrentUserOperation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetCurrentUserOperationImpl implements GetCurrentUserOperation {

    private final ReadOperation<User> readUserOperation;
    private final GetCurrentUserPrincipalOperation getCurrentUserPrincipalOperation;

    @Override
    public User execute() {
        final Long userId = getCurrentUserPrincipalOperation.execute().getId();
        return readUserOperation.execute(userId);
    }
}
