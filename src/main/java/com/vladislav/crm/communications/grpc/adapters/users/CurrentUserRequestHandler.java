package com.vladislav.crm.communications.grpc.adapters.users;

import com.google.protobuf.Empty;
import com.proto.users.CurrentUserResponse;
import com.vladislav.crm.communications.grpc.adapters.RequestHandlerAdapter;

public interface CurrentUserRequestHandler extends RequestHandlerAdapter<Empty, CurrentUserResponse> {
}
