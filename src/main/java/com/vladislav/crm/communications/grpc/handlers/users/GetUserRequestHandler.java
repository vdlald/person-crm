package com.vladislav.crm.communications.grpc.handlers.users;

import com.proto.users.GetUserRequest;
import com.proto.users.GetUserResponse;
import com.vladislav.crm.communications.grpc.handlers.RequestHandler;

public interface GetUserRequestHandler extends RequestHandler<GetUserRequest, GetUserResponse> {
}
