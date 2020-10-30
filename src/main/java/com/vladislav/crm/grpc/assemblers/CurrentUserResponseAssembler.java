package com.vladislav.crm.grpc.assemblers;

import com.proto.users.CurrentUserResponse;
import com.vladislav.crm.entities.User;

public interface CurrentUserResponseAssembler extends MessageAssembler<CurrentUserResponse, User> {
}
