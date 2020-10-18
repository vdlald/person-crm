package com.vladislav.crm.grpc.assemblers.impl;

import com.proto.users.UserResponse;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.grpc.assemblers.UserResponseAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserResponseAssemblerImpl implements UserResponseAssembler {

    @Override
    public UserResponse toMessage(User object) {
        return UserResponse.newBuilder()
                .setId(object.getId())
                .setUsername(object.getUsername())
                .build();
    }
}
