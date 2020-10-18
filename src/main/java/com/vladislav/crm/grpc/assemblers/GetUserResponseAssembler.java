package com.vladislav.crm.grpc.assemblers;

import com.proto.users.GetUserResponse;
import com.vladislav.crm.entities.User;

public interface GetUserResponseAssembler extends MessageAssembler<GetUserResponse, User> {
}
