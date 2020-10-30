package com.vladislav.crm.communications.web.adapters.users.impl;

import com.vladislav.crm.communications.handlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.communications.web.adapters.users.CurrentUserRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.GetCurrentUserResponseAssembler;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webCurrentUserRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserRequestHandlerAdapterImpl implements CurrentUserRequestHandlerAdapter {

    private final CurrentUserRequestHandler currentUserRequestHandler;
    private final GetCurrentUserResponseAssembler userAssembler;

    @Override
    public EntityModel<GetCurrentUserResponse> handle(Void unused) {
        return userAssembler.toModel(currentUserRequestHandler.handle());
    }

    @Override
    public EntityModel<GetCurrentUserResponse> handle() {
        return handle(null);
    }
}
