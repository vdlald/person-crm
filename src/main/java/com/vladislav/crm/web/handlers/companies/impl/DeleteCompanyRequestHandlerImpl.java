package com.vladislav.crm.web.handlers.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.web.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.web.handlers.companies.DeleteCompanyRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCompanyRequestHandlerImpl
        extends AbstractDeleteEntityRequestHandler<Company>
        implements DeleteCompanyRequestHandler {

    @Autowired
    public DeleteCompanyRequestHandlerImpl(DeleteOperation<Company> deleteOperation) {
        super(deleteOperation);
    }
}
