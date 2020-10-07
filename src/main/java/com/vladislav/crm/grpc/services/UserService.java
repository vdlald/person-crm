package com.vladislav.crm.grpc.services;

import com.google.common.base.Function;
import com.proto.users.*;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private final GetCurrentUserOperation getCurrentUserOperation;

    @Override
    public void currentUser(CurrentUserRequest request, StreamObserver<CurrentUserResponse> responseObserver) {
        final User user = getCurrentUserOperation.execute();
        final UserInfo info = user.getInfo();

        final Function<String, String> function = (String s) -> Optional.ofNullable(s).orElse("");

        final CurrentUserResponse response = CurrentUserResponse.newBuilder()
                .setUser(UserResponse.newBuilder()
                        .setId(user.getId())
                        .setUsername(user.getUsername())
                        .build())
                .setInfo(UserInfoResponse.newBuilder()
                        .setEmail(function.apply(info.getEmail()))
                        .setFirstname(function.apply(info.getFirstname()))
                        .setLastname(function.apply(info.getLastname()))
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
