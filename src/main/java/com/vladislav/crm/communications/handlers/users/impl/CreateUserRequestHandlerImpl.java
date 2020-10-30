package com.vladislav.crm.communications.handlers.users.impl;

import com.vladislav.crm.communications.handlers.users.CreateUserRequestHandler;
import com.vladislav.crm.communications.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserRequestHandlerImpl implements CreateUserRequestHandler {

    private final CreateOperation<User> createUserOperation;

    @Override
    public User handle(CreateUserRequest request) {
        return handle(request.getUsername(), request.getPassword());
    }

    @Override
    public User handle(String username, String password) {
        final User newUser = new User()
                .setUsername(username)
                .setPassword(password);

        return createUserOperation.execute(newUser);
    }
}
