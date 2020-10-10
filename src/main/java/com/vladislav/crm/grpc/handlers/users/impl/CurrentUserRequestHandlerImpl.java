package com.vladislav.crm.grpc.handlers.users.impl;

import com.proto.users.CurrentUserRequest;
import com.proto.users.CurrentUserResponse;
import com.proto.users.UserInfoResponse;
import com.proto.users.UserResponse;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.grpc.handlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("grpcCurrentUserRequestHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserRequestHandlerImpl implements CurrentUserRequestHandler {

    private final GetCurrentUserOperation getCurrentUserOperation;

    @Override
    public CurrentUserResponse handle(CurrentUserRequest ignore) {
        final User user = getCurrentUserOperation.execute();
        final UserInfo info = user.getInfo();

        return CurrentUserResponse.newBuilder()
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
