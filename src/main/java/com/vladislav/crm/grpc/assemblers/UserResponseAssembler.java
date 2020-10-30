package com.vladislav.crm.grpc.assemblers;

import com.proto.users.UserResponse;
import com.vladislav.crm.entities.User;

public interface UserResponseAssembler extends MessageAssembler<UserResponse, User> {
}
