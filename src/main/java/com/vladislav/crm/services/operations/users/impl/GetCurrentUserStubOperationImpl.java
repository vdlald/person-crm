package com.vladislav.crm.services.operations.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.GetCurrentUserPrincipalOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service("getCurrentUserStubOperation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetCurrentUserStubOperationImpl implements GetCurrentUserOperation {

    private final EntityManager entityManager;
    private final GetCurrentUserPrincipalOperation getCurrentUserPrincipalOperation;

    @Override
    public User execute() {
        final User principal = getCurrentUserPrincipalOperation.execute();
        return entityManager.getReference(User.class, principal.getId());
    }
}
