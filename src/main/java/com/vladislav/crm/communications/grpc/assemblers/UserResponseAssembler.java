package com.vladislav.crm.communications.grpc.assemblers;

import com.proto.users.UserResponse;
import com.vladislav.crm.entities.User;

public interface UserResponseAssembler extends MessageAssembler<UserResponse, User> {
}
