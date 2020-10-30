package com.vladislav.crm.communications.grpc.adapters.users.impl;

import com.google.protobuf.Empty;
import com.proto.users.CurrentUserResponse;
import com.vladislav.crm.communications.grpc.adapters.users.CurrentUserRequestHandlerAdapter;
import com.vladislav.crm.communications.grpc.assemblers.CurrentUserResponseAssembler;
import com.vladislav.crm.communications.handlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcCurrentUserRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserRequestHandlerAdapterImpl implements CurrentUserRequestHandlerAdapter {

    private final CurrentUserRequestHandler requestHandler;
    private final CurrentUserResponseAssembler currentUserResponseAssembler;

    @Override
    public CurrentUserResponse handle(Empty ignore) {
        final User user = requestHandler.handle();
        return currentUserResponseAssembler.toMessage(user);
    }
}
