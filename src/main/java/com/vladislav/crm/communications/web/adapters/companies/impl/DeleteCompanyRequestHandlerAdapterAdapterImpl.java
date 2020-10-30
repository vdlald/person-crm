package com.vladislav.crm.communications.web.adapters.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.companies.DeleteCompanyRequestHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCompanyRequestHandlerAdapterAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Company>
        implements DeleteCompanyRequestHandlerAdapter {

    @Autowired
    public DeleteCompanyRequestHandlerAdapterAdapterImpl(DeleteOperation<Company> deleteOperation) {
        super(deleteOperation);
    }
}
