package com.vladislav.crm.grpc.services;

import com.proto.users.*;
import com.vladislav.crm.grpc.handlers.users.CreateUserRequestHandler;
import com.vladislav.crm.grpc.handlers.users.CurrentUserRequestHandler;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private final CurrentUserRequestHandler currentUserRequestHandler;
    private final CreateUserRequestHandler createUserRequestHandler;

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
}
