package com.vladislav.crm.web.handlers.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.web.assemblers.GetCurrentUserResponseAssembler;
import com.vladislav.crm.web.handlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.web.responses.GetCurrentUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserRequestHandlerImpl implements CurrentUserRequestHandler {

    private final GetCurrentUserOperation getCurrentUserOperation;
    private final GetCurrentUserResponseAssembler userAssembler;

    @Override
    public EntityModel<GetCurrentUserResponse> handle(Void unused) {
        final User user = getCurrentUserOperation.execute();
        return userAssembler.toModel(user);
    }

    @Override
    public EntityModel<GetCurrentUserResponse> handle() {
        return handle(null);
    }
}
