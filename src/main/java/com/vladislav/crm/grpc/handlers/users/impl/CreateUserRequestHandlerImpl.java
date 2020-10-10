package com.vladislav.crm.grpc.handlers.users.impl;

import com.proto.users.*;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.grpc.handlers.users.CreateUserRequestHandler;
import com.vladislav.crm.services.operations.CreateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcCreateUserRequestHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserRequestHandlerImpl implements CreateUserRequestHandler {

    private final CreateOperation<User> userCreateOperation;

    @Override
    public CreateUserResponse handle(CreateUserRequest request) {
        final User newUser = new User().setUsername(request.getUsername()).setPassword(request.getPassword());
        final User user = userCreateOperation.execute(newUser);

        final UserResponse userResponse = UserResponse.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .build();

        return CreateUserResponse.newBuilder()
                .setUser(userResponse)
                .build();
    }
}
