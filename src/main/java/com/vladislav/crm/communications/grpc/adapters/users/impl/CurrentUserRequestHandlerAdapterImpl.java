package com.vladislav.crm.communications.grpc.adapters.users.impl;

import com.google.protobuf.Empty;
import com.proto.users.CurrentUserResponse;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.communications.grpc.assemblers.CurrentUserResponseAssembler;
import com.vladislav.crm.communications.grpc.adapters.users.CurrentUserRequestHandlerAdapter;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcCurrentUserRequestHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserRequestHandlerAdapterImpl implements CurrentUserRequestHandlerAdapter {

    private final GetCurrentUserOperation getCurrentUserOperation;
    private final CurrentUserResponseAssembler currentUserResponseAssembler;

    @Override
    public CurrentUserResponse handle(Empty ignore) {
        final User user = getCurrentUserOperation.execute();
        return currentUserResponseAssembler.toMessage(user);
    }
}
