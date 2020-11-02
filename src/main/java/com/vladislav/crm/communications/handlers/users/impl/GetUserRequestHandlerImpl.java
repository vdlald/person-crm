package com.vladislav.crm.communications.handlers.users.impl;

import com.vladislav.crm.communications.handlers.users.GetUserRequestHandler;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserRequestHandlerImpl implements GetUserRequestHandler {

    private final ReadOperation<User> userReadOperation;

    @Override
    @PreAuthorize("@userOwnsReadAllAuthorization.hasAuthorization()")
    public User handle(Long userId) {
        return userReadOperation.execute(userId);
    }
}
