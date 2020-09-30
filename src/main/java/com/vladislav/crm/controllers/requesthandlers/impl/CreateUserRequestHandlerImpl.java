package com.vladislav.crm.controllers.requesthandlers.impl;

import com.vladislav.crm.controllers.assemblers.UserAssembler;
import com.vladislav.crm.controllers.requesthandlers.CreateUserRequestHandler;
import com.vladislav.crm.controllers.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.users.CreateUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserRequestHandlerImpl implements CreateUserRequestHandler {

    private final UserAssembler userAssembler;
    private final CreateUserOperation createUserOperation;

    @Override
    public EntityModel<User> handle(CreateUserRequest request) {
        final User newUser = new User()
                .setUsername(request.getUsername())
                .setPassword(request.getPassword());

        final User saved = createUserOperation.execute(newUser);

        return userAssembler.toModel(saved);
    }
}
