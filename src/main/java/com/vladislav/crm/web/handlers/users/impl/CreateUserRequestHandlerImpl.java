package com.vladislav.crm.web.handlers.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.web.assemblers.GetCurrentUserResponseAssembler;
import com.vladislav.crm.web.handlers.users.CreateUserRequestHandler;
import com.vladislav.crm.web.requests.CreateUserRequest;
import com.vladislav.crm.web.responses.GetCurrentUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserRequestHandlerImpl implements CreateUserRequestHandler {

    private final GetCurrentUserResponseAssembler userAssembler;
    private final CreateOperation<User> createUserOperation;

    @Override
    public EntityModel<GetCurrentUserResponse> handle(CreateUserRequest request) {
        final User newUser = new User()
                .setUsername(request.getUsername())
                .setPassword(request.getPassword());

        final User saved = createUserOperation.execute(newUser);

        return userAssembler.toModel(saved);
    }
}
