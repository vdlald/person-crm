package com.vladislav.crm.web.handlers.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.web.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.web.handlers.companies.ReadCompanyRequestHandler;
import com.vladislav.crm.web.responses.CompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReadCompanyRequestHandlerImpl
        extends AbstractReadEntityRequestHandler<Company, CompanyResponse>
        implements ReadCompanyRequestHandler {

    @Autowired
    public ReadCompanyRequestHandlerImpl(
            RepresentationModelAssembler<Company, EntityModel<CompanyResponse>> assembler,
            ReadOperation<Company> readOperation
    ) {
        super(assembler, readOperation);
    }
}
