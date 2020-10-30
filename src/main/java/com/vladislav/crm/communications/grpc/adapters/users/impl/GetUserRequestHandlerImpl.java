package com.vladislav.crm.communications.grpc.adapters.users.impl;

import com.proto.users.GetUserRequest;
import com.proto.users.GetUserResponse;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.communications.grpc.assemblers.GetUserResponseAssembler;
import com.vladislav.crm.communications.grpc.adapters.users.GetUserRequestHandler;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcGetUserRequestHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserRequestHandlerImpl implements GetUserRequestHandler {

    private final ReadOperation<User> userReadOperation;
    private final GetUserResponseAssembler getUserResponseAssembler;

    @Override
    public GetUserResponse handle(GetUserRequest request) {
        final User user = userReadOperation.execute(request.getUserId());
        return getUserResponseAssembler.toMessage(user);
    }
}
