package com.vladislav.crm.communications.web.adapters.companies.impl;

import com.vladislav.crm.communications.handlers.companies.CreateCompanyRequestHandler;
import com.vladislav.crm.communications.web.adapters.companies.CreateCompanyRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadCompanyResponseAssembler;
import com.vladislav.crm.communications.web.requests.CreateCompanyRequest;
import com.vladislav.crm.communications.web.responses.ReadCompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webCreateCompanyRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateCompanyRequestHandlerAdapterImpl implements CreateCompanyRequestHandlerAdapter {

    private final ReadCompanyResponseAssembler readCompanyResponseAssembler;
    private final CreateCompanyRequestHandler requestHandler;

    @Override
    public EntityModel<ReadCompanyResponse> handle(CreateCompanyRequest request) {
        return readCompanyResponseAssembler.toModel(requestHandler.handle(request.toCommunicationRequest()));
    }
}
