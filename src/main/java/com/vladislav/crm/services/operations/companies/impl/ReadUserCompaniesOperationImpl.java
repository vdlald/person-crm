package com.vladislav.crm.services.operations.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.repositories.CompanyRepository;
import com.vladislav.crm.services.operations.companies.ReadUserCompaniesOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserCompaniesOperationImpl implements ReadUserCompaniesOperation {

    private final CompanyRepository companyRepository;

    @Override
    public Collection<Company> execute(Long userId) {
        return companyRepository.findAllByUserId(userId);
    }
}
