package com.vladislav.crm.communications.web.adapters.companies.impl;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractReadEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.companies.ReadCompanyRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import com.vladislav.crm.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component("webReadCompanyRequestHandlerAdapter")
public class ReadCompanyRequestHandlerAdapterImpl
        extends AbstractReadEntityRequestHandlerAdapter<Company, CompanyResponse>
        implements ReadCompanyRequestHandlerAdapter {

    @Autowired
    public ReadCompanyRequestHandlerAdapterImpl(
            RepresentationModelAssembler<Company, EntityModel<CompanyResponse>> assembler,
            AbstractReadEntityRequestHandler<Company> requestHandler
    ) {
        super(assembler, requestHandler);
    }
}
