package com.vladislav.crm.services.operations.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.repositories.CompanyRepository;
import com.vladislav.crm.services.operations.DeleteOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteCompanyOperationImpl implements DeleteOperation<Company> {

    private final CompanyRepository companyRepository;

    @Override
    public void execute(@NonNull Long id) {
        final Company company = companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        company.getContacts().forEach(contact -> contact.setCompanyUnsafe(null));
        companyRepository.save(company);
    }
}
