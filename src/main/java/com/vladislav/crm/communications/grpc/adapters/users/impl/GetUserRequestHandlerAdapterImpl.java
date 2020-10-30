package com.vladislav.crm.communications.grpc.adapters.users.impl;

import com.proto.users.GetUserRequest;
import com.proto.users.GetUserResponse;
import com.vladislav.crm.communications.grpc.adapters.users.GetUserRequestHandlerAdapter;
import com.vladislav.crm.communications.grpc.assemblers.GetUserResponseAssembler;
import com.vladislav.crm.communications.handlers.users.GetUserRequestHandler;
import com.vladislav.crm.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcGetUserRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserRequestHandlerAdapterImpl implements GetUserRequestHandlerAdapter {

    private final GetUserRequestHandler requestHandler;
    private final GetUserResponseAssembler getUserResponseAssembler;

    @Override
    public GetUserResponse handle(GetUserRequest request) {
        final User user = requestHandler.handle(request.getUserId());
        return getUserResponseAssembler.toMessage(user);
    }
}
