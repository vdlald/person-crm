package com.vladislav.crm.communications.grpc.adapters.users;

import com.google.protobuf.Empty;
import com.proto.users.CurrentUserResponse;
import com.vladislav.crm.communications.grpc.adapters.RequestHandlerAdapter;

public interface CurrentUserRequestHandlerAdapter extends RequestHandlerAdapter<Empty, CurrentUserResponse> {
}
