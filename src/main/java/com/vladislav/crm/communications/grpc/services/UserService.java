package com.vladislav.crm.communications.grpc.services;

import com.google.protobuf.Empty;
import com.proto.users.*;
import com.vladislav.crm.communications.grpc.GrpcServiceUtils;
import com.vladislav.crm.communications.grpc.adapters.users.CreateUserRequestHandlerAdapter;
import com.vladislav.crm.communications.grpc.adapters.users.CurrentUserRequestHandlerAdapter;
import com.vladislav.crm.communications.grpc.adapters.users.GetUserRequestHandlerAdapter;
import com.vladislav.crm.communications.grpc.adapters.users.UpdateUserInfoRequestHandlerAdapter;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private final CurrentUserRequestHandlerAdapter currentUserRequestHandlerAdapter;
    private final CreateUserRequestHandlerAdapter createUserRequestHandlerAdapter;
    private final GetUserRequestHandlerAdapter getUserRequestHandlerAdapter;
    private final UpdateUserInfoRequestHandlerAdapter updateUserInfoRequestHandlerAdapter;

    @Override
    public void currentUser(Empty request, StreamObserver<CurrentUserResponse> responseObserver) {
        GrpcServiceUtils.handle(currentUserRequestHandlerAdapter, request, responseObserver);
    }

    @Override
    public void updateUserInfo(UpdateCurrentUserInfoRequest request, StreamObserver<Empty> responseObserver) {
        GrpcServiceUtils.handle(updateUserInfoRequestHandlerAdapter, request, responseObserver);
    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<CreateUserResponse> responseObserver) {
        GrpcServiceUtils.handle(createUserRequestHandlerAdapter, request, responseObserver);
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        GrpcServiceUtils.handle(getUserRequestHandlerAdapter, request, responseObserver);
    }
}
