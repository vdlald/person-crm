package com.vladislav.crm.communications.handlers.companies.impl;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.communications.handlers.companies.ReadCompanyRequestHandler;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.ReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadCompanyRequestHandlerImpl
        extends AbstractReadEntityRequestHandler<Company>
        implements ReadCompanyRequestHandler {

    @Autowired
    public ReadCompanyRequestHandlerImpl(
            ReadOperation<Company> readCompanyOperation
    ) {
        super(readCompanyOperation);
    }
}
