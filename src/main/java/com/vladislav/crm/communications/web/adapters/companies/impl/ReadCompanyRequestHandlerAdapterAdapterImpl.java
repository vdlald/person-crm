package com.vladislav.crm.communications.web.adapters.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.communications.web.adapters.AbstractReadEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.companies.ReadCompanyRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReadCompanyRequestHandlerAdapterAdapterImpl
        extends AbstractReadEntityRequestHandlerAdapter<Company, CompanyResponse>
        implements ReadCompanyRequestHandlerAdapter {

    @Autowired
    public ReadCompanyRequestHandlerAdapterAdapterImpl(
            RepresentationModelAssembler<Company, EntityModel<CompanyResponse>> assembler,
            ReadOperation<Company> readCompanyOperation
    ) {
        super(assembler, readCompanyOperation);
    }
}
