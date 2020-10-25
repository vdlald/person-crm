package com.vladislav.crm.services.operations.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service("readCompanyStubOperation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadCompanyStubOperationImpl implements ReadOperation<Company> {

    private final EntityManager entityManager;

    @Override
    public Company execute(Long id) {
        return entityManager.getReference(Company.class, id);
    }
}
