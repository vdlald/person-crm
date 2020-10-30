package com.vladislav.crm.grpc.handlers.users.impl;

import com.google.protobuf.Empty;
import com.proto.users.CurrentUserResponse;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.grpc.assemblers.CurrentUserResponseAssembler;
import com.vladislav.crm.grpc.handlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcCurrentUserRequestHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserRequestHandlerImpl implements CurrentUserRequestHandler {

    private final GetCurrentUserOperation getCurrentUserOperation;
    private final CurrentUserResponseAssembler currentUserResponseAssembler;

    @Override
    public CurrentUserResponse handle(Empty ignore) {
        final User user = getCurrentUserOperation.execute();
        return currentUserResponseAssembler.toMessage(user);
    }
}
