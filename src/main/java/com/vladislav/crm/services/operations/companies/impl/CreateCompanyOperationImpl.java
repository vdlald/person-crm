package com.vladislav.crm.services.operations.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.abstractions.AbstractCreateOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyOperationImpl extends AbstractCreateOperation<Company> {

    @Autowired
    public CreateCompanyOperationImpl(JpaRepository<Company, Long> repository) {
        super(repository);
    }
}
