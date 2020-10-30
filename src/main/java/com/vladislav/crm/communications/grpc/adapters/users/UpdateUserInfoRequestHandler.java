package com.vladislav.crm.communications.grpc.adapters.users;

import com.google.protobuf.Empty;
import com.proto.users.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.communications.grpc.adapters.RequestHandlerAdapter;

public interface UpdateUserInfoRequestHandler extends RequestHandlerAdapter<UpdateCurrentUserInfoRequest, Empty> {
}
