package com.vladislav.crm.communications.web.adapters.statuses.impl;

import com.vladislav.crm.communications.handlers.statuses.CreateStatusRequestHandler;
import com.vladislav.crm.communications.web.adapters.statuses.CreateStatusRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadStatusResponseAssembler;
import com.vladislav.crm.communications.web.requests.CreateStatusRequest;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webCreateStatusRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateStatusRequestHandlerAdapterImpl implements CreateStatusRequestHandlerAdapter {

    private final ReadStatusResponseAssembler readStatusResponseAssembler;
    private final CreateStatusRequestHandler createStatusRequestHandler;

    @Override
    public EntityModel<ReadStatusResponse> handle(CreateStatusRequest request) {
        return readStatusResponseAssembler.toModel(createStatusRequestHandler.handle(request.toCommunicationRequest()));
    }
}
