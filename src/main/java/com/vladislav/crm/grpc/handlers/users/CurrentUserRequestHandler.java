package com.vladislav.crm.grpc.handlers.users;

import com.google.protobuf.Empty;
import com.proto.users.CurrentUserResponse;
import com.vladislav.crm.grpc.handlers.RequestHandler;

public interface CurrentUserRequestHandler extends RequestHandler<Empty, CurrentUserResponse> {
}
