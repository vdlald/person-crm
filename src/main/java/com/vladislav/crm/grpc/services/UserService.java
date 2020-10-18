package com.vladislav.crm.grpc.services;

import com.proto.users.*;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.grpc.handlers.users.CreateUserRequestHandler;
import com.vladislav.crm.grpc.handlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.services.operations.ReadOperation;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private final CurrentUserRequestHandler currentUserRequestHandler;
    private final CreateUserRequestHandler createUserRequestHandler;
    private final ReadOperation<User> userReadOperation;

    @Override
    public void currentUser(CurrentUserRequest request, StreamObserver<CurrentUserResponse> responseObserver) {
        final CurrentUserResponse response = currentUserRequestHandler.handle(request);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<CreateUserResponse> responseObserver) {
        final CreateUserResponse response = createUserRequestHandler.handle(request);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        final User user = userReadOperation.execute(request.getUserId());
        final UserInfo info = user.getInfo();

        responseObserver.onNext(
                GetUserResponse.newBuilder()
                        .setUser(UserResponse.newBuilder()
                                .setId(user.getId())
                                .setUsername(user.getUsername())
                                .build())
                        .setInfo(UserInfoResponse.newBuilder()
                                .setEmail(Objects.requireNonNullElse(info.getEmail(), ""))
                                .setFirstname(Objects.requireNonNullElse(info.getFirstname(), ""))
                                .setLastname(Objects.requireNonNullElse(info.getLastname(), ""))
                                .build())
                        .build()
        );
        responseObserver.onCompleted();
    }
}
