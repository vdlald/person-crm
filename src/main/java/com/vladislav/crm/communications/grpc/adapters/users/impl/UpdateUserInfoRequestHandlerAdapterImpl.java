package com.vladislav.crm.communications.grpc.adapters.users.impl;

import com.google.protobuf.Empty;
import com.proto.users.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.communications.grpc.adapters.users.UpdateUserInfoRequestHandlerAdapter;
import com.vladislav.crm.communications.handlers.users.UpdateCurrentUserInfoRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcUpdateUserInfoRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateUserInfoRequestHandlerAdapterImpl implements UpdateUserInfoRequestHandlerAdapter {

    private final UpdateCurrentUserInfoRequestHandler requestHandler;

    @Override
    public Empty handle(UpdateCurrentUserInfoRequest request) {
        final var adaptedRequest = new com.vladislav.crm.communications.requests.UpdateCurrentUserInfoRequest()
                .setEmail(request.getEmail())
                .setFirstname(request.getFirstname())
                .setLastname(request.getLastname());
        requestHandler.handle(adaptedRequest);
        return Empty.getDefaultInstance();
    }
}
