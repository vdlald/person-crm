package com.vladislav.crm.grpc.handlers.users;

import com.proto.users.GetUserRequest;
import com.proto.users.GetUserResponse;
import com.vladislav.crm.grpc.handlers.RequestHandler;

public interface GetUserRequestHandler extends RequestHandler<GetUserRequest, GetUserResponse> {
}
