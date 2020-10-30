package com.vladislav.crm.communications.handlers.users.impl;

import com.vladislav.crm.communications.handlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserRequestHandlerImpl implements CurrentUserRequestHandler {

    private final GetCurrentUserOperation getCurrentUserOperation;

    @Override
    public User handle(Void unused) {
        return getCurrentUserOperation.execute();
    }

    @Override
    public User handle() {
        return handle(null);
    }
}
