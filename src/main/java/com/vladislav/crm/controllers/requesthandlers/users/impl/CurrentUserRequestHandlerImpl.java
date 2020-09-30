package com.vladislav.crm.controllers.requesthandlers.users.impl;

import com.vladislav.crm.controllers.assemblers.UserAssembler;
import com.vladislav.crm.controllers.requesthandlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserRequestHandlerImpl implements CurrentUserRequestHandler {

    private final GetCurrentUserOperation getCurrentUserOperation;
    private final UserAssembler userAssembler;

    @Override
    public EntityModel<User> handle(Void unused) {
        final User user = getCurrentUserOperation.execute();
        return userAssembler.toModel(user);
    }

    @Override
    public EntityModel<User> handle() {
        return handle(null);
    }
}
