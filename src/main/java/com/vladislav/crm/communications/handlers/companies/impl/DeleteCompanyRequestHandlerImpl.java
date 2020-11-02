package com.vladislav.crm.communications.handlers.companies.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.handlers.companies.DeleteCompanyRequestHandler;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.DeleteOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DeleteCompanyRequestHandlerImpl
        extends AbstractDeleteEntityRequestHandler<Company>
        implements DeleteCompanyRequestHandler {

    @Autowired
    public DeleteCompanyRequestHandlerImpl(DeleteOperation<Company> deleteOperation) {
        super(deleteOperation);
    }

    @Override
    @PreAuthorize("@userOwnsCompanyAuthorization.hasAuthorization(#id)")
    public Void handle(Long id) {
        return super.handle(id);
    }
}
