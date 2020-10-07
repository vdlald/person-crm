package com.vladislav.crm.services.operations.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetCurrentUserStubOperationImpl implements GetCurrentUserStubOperation {

    @Override
    public User execute() {
        return (User) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new AccessDeniedException("No credentials was provided"))
                .getPrincipal();
    }
}
