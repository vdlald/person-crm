package com.vladislav.crm.grpc.handlers.users.impl;

import com.proto.users.*;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.grpc.handlers.users.GetUserRequestHandler;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("grpcGetUserRequestHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserRequestHandlerImpl implements GetUserRequestHandler {

    private final ReadOperation<User> userReadOperation;

    @Override
    public GetUserResponse handle(GetUserRequest request) {
        final User user = userReadOperation.execute(request.getUserId());
        final UserInfo info = user.getInfo();

        return GetUserResponse.newBuilder()
                .setUser(UserResponse.newBuilder()
                        .setId(user.getId())
                        .setUsername(user.getUsername())
                        .build())
                .setInfo(UserInfoResponse.newBuilder()
                        .setEmail(Objects.requireNonNullElse(info.getEmail(), ""))
                        .setFirstname(Objects.requireNonNullElse(info.getFirstname(), ""))
                        .setLastname(Objects.requireNonNullElse(info.getLastname(), ""))
                        .build())
                .build();
    }
}
