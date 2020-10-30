package com.vladislav.crm.communications.grpc.adapters.users;

import com.proto.users.GetUserRequest;
import com.proto.users.GetUserResponse;
import com.vladislav.crm.communications.grpc.adapters.RequestHandlerAdapter;

public interface GetUserRequestHandler extends RequestHandlerAdapter<GetUserRequest, GetUserResponse> {
}
