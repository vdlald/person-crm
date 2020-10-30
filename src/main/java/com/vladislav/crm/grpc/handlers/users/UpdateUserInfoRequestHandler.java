package com.vladislav.crm.grpc.handlers.users;

import com.google.protobuf.Empty;
import com.proto.users.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.grpc.handlers.RequestHandler;

public interface UpdateUserInfoRequestHandler extends RequestHandler<UpdateCurrentUserInfoRequest, Empty> {
}
