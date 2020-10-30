package com.vladislav.crm.communications.grpc.assemblers;

import com.proto.users.CurrentUserResponse;
import com.vladislav.crm.entities.User;

public interface CurrentUserResponseAssembler extends MessageAssembler<CurrentUserResponse, User> {
}
