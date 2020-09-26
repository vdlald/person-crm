package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ReadCompanyOperationImpl implements ReadCompanyOperation {

    private final CompanyRepository companyRepository;

    @Override
    public Company execute(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(EntityNotFoundException::new);
    }
}
