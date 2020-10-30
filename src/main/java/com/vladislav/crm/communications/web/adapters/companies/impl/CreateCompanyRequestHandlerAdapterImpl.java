package com.vladislav.crm.communications.web.adapters.companies.impl;

import com.vladislav.crm.communications.handlers.companies.CreateCompanyRequestHandler;
import com.vladislav.crm.communications.web.adapters.companies.CreateCompanyRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.CompanyResponseAssembler;
import com.vladislav.crm.communications.web.requests.CreateCompanyRequest;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webCreateCompanyRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateCompanyRequestHandlerAdapterImpl implements CreateCompanyRequestHandlerAdapter {

    private final CompanyResponseAssembler companyResponseAssembler;
    private final CreateCompanyRequestHandler requestHandler;

    @Override
    public EntityModel<CompanyResponse> handle(CreateCompanyRequest request) {
        return companyResponseAssembler.toModel(requestHandler.handle(request.toCommunicationRequest()));
    }
}
