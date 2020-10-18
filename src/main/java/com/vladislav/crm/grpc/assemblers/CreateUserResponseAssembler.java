package com.vladislav.crm.grpc.assemblers;

import com.proto.users.CreateUserResponse;
import com.vladislav.crm.entities.User;

public interface CreateUserResponseAssembler extends MessageAssembler<CreateUserResponse, User> {
}
