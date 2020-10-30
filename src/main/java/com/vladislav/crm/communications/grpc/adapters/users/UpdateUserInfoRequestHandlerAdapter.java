package com.vladislav.crm.communications.grpc.adapters.users;

import com.google.protobuf.Empty;
import com.proto.users.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.communications.grpc.adapters.RequestHandlerAdapter;

public interface UpdateUserInfoRequestHandlerAdapter extends RequestHandlerAdapter<UpdateCurrentUserInfoRequest, Empty> {
}
