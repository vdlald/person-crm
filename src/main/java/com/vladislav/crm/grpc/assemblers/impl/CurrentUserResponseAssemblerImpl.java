package com.vladislav.crm.grpc.assemblers.impl;

import com.proto.users.CurrentUserResponse;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.grpc.assemblers.CurrentUserResponseAssembler;
import com.vladislav.crm.grpc.assemblers.UserInfoResponseAssembler;
import com.vladislav.crm.grpc.assemblers.UserResponseAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserResponseAssemblerImpl implements CurrentUserResponseAssembler {

    private final UserResponseAssembler userResponseAssembler;
    private final UserInfoResponseAssembler userInfoResponseAssembler;

    @Override
    public CurrentUserResponse toMessage(User object) {
        return CurrentUserResponse.newBuilder()
                .setUser(userResponseAssembler.toMessage(object))
                .setInfo(userInfoResponseAssembler.toMessage(object.getInfo()))
                .build();
    }
}
