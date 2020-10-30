package com.vladislav.crm.communications.web.adapters.users.impl;

import com.vladislav.crm.communications.handlers.users.CreateUserRequestHandler;
import com.vladislav.crm.communications.web.adapters.users.CreateUserRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.GetCurrentUserResponseAssembler;
import com.vladislav.crm.communications.web.requests.CreateUserRequest;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
import com.vladislav.crm.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserRequestHandlerAdapterImpl implements CreateUserRequestHandlerAdapter {

    private final GetCurrentUserResponseAssembler userAssembler;
    private final CreateUserRequestHandler createUserRequestHandler;

    @Override
    public EntityModel<GetCurrentUserResponse> handle(CreateUserRequest request) {
        final User user = createUserRequestHandler.handle(request.getUsername(), request.getPassword());
        return userAssembler.toModel(user);
    }
}
