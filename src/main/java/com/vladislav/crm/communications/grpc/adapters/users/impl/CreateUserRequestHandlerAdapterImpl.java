package com.vladislav.crm.communications.grpc.adapters.users.impl;

import com.proto.users.CreateUserRequest;
import com.proto.users.CreateUserResponse;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.communications.grpc.assemblers.CreateUserResponseAssembler;
import com.vladislav.crm.communications.grpc.adapters.users.CreateUserRequestHandlerAdapter;
import com.vladislav.crm.services.operations.CreateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcCreateUserRequestHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserRequestHandlerAdapterImpl implements CreateUserRequestHandlerAdapter {

    private final CreateOperation<User> userCreateOperation;
    private final CreateUserResponseAssembler createUserResponseAssembler;

    @Override
    public CreateUserResponse handle(CreateUserRequest request) {
        final User newUser = new User().setUsername(request.getUsername()).setPassword(request.getPassword());
        final User user = userCreateOperation.execute(newUser);

        return createUserResponseAssembler.toMessage(user);
    }
}
