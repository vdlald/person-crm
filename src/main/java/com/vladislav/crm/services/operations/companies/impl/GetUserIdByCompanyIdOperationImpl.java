package com.vladislav.crm.services.operations.companies.impl;

import com.vladislav.crm.repositories.CompanyRepository;
import com.vladislav.crm.services.operations.companies.GetUserIdByCompanyIdOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserIdByCompanyIdOperationImpl implements GetUserIdByCompanyIdOperation {

    private final CompanyRepository companyRepository;

    @Override
    public long execute(long companyId) {
        return companyRepository.findUserIdById(companyId).orElseThrow(EntityNotFoundException::new);
    }
}
