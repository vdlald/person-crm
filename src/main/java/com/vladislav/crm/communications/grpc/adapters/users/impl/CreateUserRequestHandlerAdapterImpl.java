package com.vladislav.crm.communications.grpc.adapters.users.impl;

import com.proto.users.CreateUserRequest;
import com.proto.users.CreateUserResponse;
import com.vladislav.crm.communications.grpc.adapters.users.CreateUserRequestHandlerAdapter;
import com.vladislav.crm.communications.grpc.assemblers.CreateUserResponseAssembler;
import com.vladislav.crm.communications.handlers.users.CreateUserRequestHandler;
import com.vladislav.crm.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcCreateUserRequestHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserRequestHandlerAdapterImpl implements CreateUserRequestHandlerAdapter {

    private final CreateUserRequestHandler requestHandler;
    private final CreateUserResponseAssembler createUserResponseAssembler;

    @Override
    public CreateUserResponse handle(CreateUserRequest request) {
        final User user = requestHandler.handle(request.getUsername(), request.getPassword());
        return createUserResponseAssembler.toMessage(user);
    }
}
