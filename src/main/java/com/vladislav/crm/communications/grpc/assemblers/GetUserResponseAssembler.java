package com.vladislav.crm.communications.grpc.assemblers;

import com.proto.users.GetUserResponse;
import com.vladislav.crm.entities.User;

public interface GetUserResponseAssembler extends MessageAssembler<GetUserResponse, User> {
}
