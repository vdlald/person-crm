package com.vladislav.crm.grpc.assemblers.impl;

import com.proto.users.UserInfoResponse;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.grpc.assemblers.UserInfoResponseAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserInfoResponseAssemblerImpl implements UserInfoResponseAssembler {

    @Override
    public UserInfoResponse toMessage(UserInfo object) {
        return UserInfoResponse.newBuilder()
                .setEmail(Objects.requireNonNullElse(object.getEmail(), ""))
                .setFirstname(Objects.requireNonNullElse(object.getFirstname(), ""))
                .setLastname(Objects.requireNonNullElse(object.getLastname(), ""))
                .build();
    }
}
