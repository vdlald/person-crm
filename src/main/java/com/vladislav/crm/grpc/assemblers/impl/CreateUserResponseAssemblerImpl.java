package com.vladislav.crm.grpc.assemblers.impl;

import com.proto.users.CreateUserResponse;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.grpc.assemblers.CreateUserResponseAssembler;
import com.vladislav.crm.grpc.assemblers.UserResponseAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserResponseAssemblerImpl implements CreateUserResponseAssembler {

    private final UserResponseAssembler userResponseAssembler;

    @Override
    public CreateUserResponse toMessage(User object) {
        return CreateUserResponse.newBuilder()
                .setUser(userResponseAssembler.toMessage(object))
                .build();
    }
}
