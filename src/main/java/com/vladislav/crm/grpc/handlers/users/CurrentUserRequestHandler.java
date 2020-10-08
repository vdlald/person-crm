package com.vladislav.crm.grpc.handlers.users;

import com.proto.users.CurrentUserRequest;
import com.proto.users.CurrentUserResponse;
import com.vladislav.crm.grpc.handlers.RequestHandler;

public interface CurrentUserRequestHandler extends RequestHandler<CurrentUserRequest, CurrentUserResponse> {
}
