package com.vladislav.crm.communications.grpc.adapters.users;

import com.proto.users.CreateUserRequest;
import com.proto.users.CreateUserResponse;
import com.vladislav.crm.communications.grpc.adapters.RequestHandlerAdapter;

public interface CreateUserRequestHandler extends RequestHandlerAdapter<CreateUserRequest, CreateUserResponse> {
}
