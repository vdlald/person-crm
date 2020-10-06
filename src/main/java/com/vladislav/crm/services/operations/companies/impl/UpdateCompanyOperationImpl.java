package com.vladislav.crm.services.operations.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.abstractions.AbstractUpdateOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCompanyOperationImpl extends AbstractUpdateOperation<Company> {

    @Autowired
    public UpdateCompanyOperationImpl(JpaRepository<Company, Long> repository) {
        super(repository);
    }
}
