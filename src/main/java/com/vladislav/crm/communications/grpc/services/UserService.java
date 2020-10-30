package com.vladislav.crm.communications.grpc.services;

import com.google.protobuf.Empty;
import com.proto.users.*;
import com.vladislav.crm.communications.grpc.GrpcServiceUtils;
import com.vladislav.crm.communications.grpc.handlers.users.CreateUserRequestHandler;
import com.vladislav.crm.communications.grpc.handlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.communications.grpc.handlers.users.GetUserRequestHandler;
import com.vladislav.crm.communications.grpc.handlers.users.UpdateUserInfoRequestHandler;
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
    private final UpdateUserInfoRequestHandler updateUserInfoRequestHandler;

    @Override
    public void currentUser(Empty request, StreamObserver<CurrentUserResponse> responseObserver) {
        GrpcServiceUtils.handle(currentUserRequestHandler, request, responseObserver);
    }

    @Override
    public void updateUserInfo(UpdateCurrentUserInfoRequest request, StreamObserver<Empty> responseObserver) {
        GrpcServiceUtils.handle(updateUserInfoRequestHandler, request, responseObserver);
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
