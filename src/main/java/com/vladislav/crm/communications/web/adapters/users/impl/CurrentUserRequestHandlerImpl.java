package com.vladislav.crm.communications.web.adapters.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.communications.web.assemblers.GetCurrentUserResponseAssembler;
import com.vladislav.crm.communications.web.adapters.users.CurrentUserRequestHandler;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
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
