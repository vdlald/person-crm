package com.vladislav.crm.grpc.assemblers;

import com.proto.users.UserInfoResponse;
import com.vladislav.crm.entities.UserInfo;

public interface UserInfoResponseAssembler extends MessageAssembler<UserInfoResponse, UserInfo> {
}
