package com.vladislav.crm.services.operations.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetCurrentUserStubOperationImpl implements GetCurrentUserStubOperation {

    private final EntityManager entityManager;

    @Override
    public User execute() {
        final User principal = (User) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new AccessDeniedException("No credentials was provided"))
                .getPrincipal();
        return entityManager.getReference(User.class, principal.getId());
    }
}
