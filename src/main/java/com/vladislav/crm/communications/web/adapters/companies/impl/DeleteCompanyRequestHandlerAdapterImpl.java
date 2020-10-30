package com.vladislav.crm.communications.web.adapters.companies.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.companies.DeleteCompanyRequestHandlerAdapter;
import com.vladislav.crm.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("webDeleteCompanyRequestHandlerAdapter")
public class DeleteCompanyRequestHandlerAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Company>
        implements DeleteCompanyRequestHandlerAdapter {

    @Autowired
    public DeleteCompanyRequestHandlerAdapterImpl(AbstractDeleteEntityRequestHandler<Company> requestHandler) {
        super(requestHandler);
    }
}
