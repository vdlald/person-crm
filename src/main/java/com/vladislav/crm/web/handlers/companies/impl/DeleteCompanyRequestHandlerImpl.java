package com.vladislav.crm.web.handlers.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.web.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.web.handlers.companies.DeleteCompanyRequestHandler;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;

@Server
public class DeleteCompanyRequestHandlerImpl
        extends AbstractDeleteEntityRequestHandler<Company>
        implements DeleteCompanyRequestHandler {

    @Autowired
    public DeleteCompanyRequestHandlerImpl(DeleteOperation<Company> deleteOperation) {
        super(deleteOperation);
    }
}
