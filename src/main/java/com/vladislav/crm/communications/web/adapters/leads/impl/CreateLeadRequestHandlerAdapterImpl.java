package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.vladislav.crm.communications.handlers.leads.CreateLeadRequestHandler;
import com.vladislav.crm.communications.web.adapters.leads.CreateLeadRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadLeadResponseAssembler;
import com.vladislav.crm.communications.web.requests.CreateLeadRequest;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webCreateLeadRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateLeadRequestHandlerAdapterImpl implements CreateLeadRequestHandlerAdapter {

    private final ReadLeadResponseAssembler readLeadResponseAssembler;
    private final CreateLeadRequestHandler requestHandler;

    @Override
    public EntityModel<ReadLeadResponse> handle(CreateLeadRequest request) {
        return readLeadResponseAssembler.toModel(requestHandler.handle(request.toCommunicationRequest()));
    }
}
