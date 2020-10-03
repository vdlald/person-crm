package com.vladislav.crm.web.requesthandlers.users.impl;

import com.vladislav.crm.web.assemblers.UserAssembler;
import com.vladislav.crm.web.requesthandlers.users.CreateUserRequestHandler;
import com.vladislav.crm.web.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserRequestHandlerImpl implements CreateUserRequestHandler {

    private final UserAssembler userAssembler;
    private final CreateOperation<User> createUserOperation;

    @Override
    public EntityModel<User> handle(CreateUserRequest request) {
        final User newUser = new User()
                .setUsername(request.getUsername())
                .setPassword(request.getPassword());

        final User saved = createUserOperation.execute(newUser);

        return userAssembler.toModel(saved);
    }
}
