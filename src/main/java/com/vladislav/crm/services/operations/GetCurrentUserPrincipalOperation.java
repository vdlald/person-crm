package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCurrentUserPrincipalOperation {

    public User execute() {
        return (User) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new AccessDeniedException("No credentials was provided"))
                .getPrincipal();
    }
}
