package com.vladislav.crm.grpc.services;

import com.google.protobuf.Empty;
import com.proto.users.*;
import com.vladislav.crm.grpc.GrpcServiceUtils;
import com.vladislav.crm.grpc.handlers.users.CreateUserRequestHandler;
import com.vladislav.crm.grpc.handlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.grpc.handlers.users.GetUserRequestHandler;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private final CurrentUserRequestHandler currentUserRequestHandler;
    private final CreateUserRequestHandler createUserRequestHandler;
    private final GetUserRequestHandler getUserRequestHandler;

    @Override
    public void currentUser(Empty request, StreamObserver<CurrentUserResponse> responseObserver) {
        GrpcServiceUtils.handle(currentUserRequestHandler, request, responseObserver);
    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<CreateUserResponse> responseObserver) {
        GrpcServiceUtils.handle(createUserRequestHandler, request, responseObserver);
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        GrpcServiceUtils.handle(getUserRequestHandler, request, responseObserver);
    }
}
