package com.vladislav.crm.communications.web.adapters.users.impl;

import com.vladislav.crm.communications.handlers.users.UpdateCurrentUserInfoRequestHandler;
import com.vladislav.crm.communications.web.adapters.users.UpdateCurrentUserInfoRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.GetCurrentUserResponseAssembler;
import com.vladislav.crm.communications.web.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webUpdateCurrentUserInfoRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateCurrentUserInfoRequestHandlerAdapterImpl implements UpdateCurrentUserInfoRequestHandlerAdapter {

    private final UpdateCurrentUserInfoRequestHandler requestHandler;
    private final GetCurrentUserResponseAssembler getCurrentUserResponseAssembler;

    @Override
    public EntityModel<GetCurrentUserResponse> handle(UpdateCurrentUserInfoRequest request) {
        return getCurrentUserResponseAssembler.toModel(requestHandler.handle(request.toCommunicationRequest()));
    }
}
