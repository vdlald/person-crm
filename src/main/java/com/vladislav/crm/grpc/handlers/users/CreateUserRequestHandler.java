package com.vladislav.crm.grpc.handlers.users;

import com.proto.users.CreateUserRequest;
import com.proto.users.CreateUserResponse;
import com.vladislav.crm.grpc.handlers.RequestHandler;

public interface CreateUserRequestHandler extends RequestHandler<CreateUserRequest, CreateUserResponse> {
}
