package com.vladislav.crm.services.operations.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.repositories.CompanyRepository;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteCompanyOperationImpl implements DeleteOperation<Company> {

    private final CompanyRepository companyRepository;
    private final ReadOperation<Company> readCompanyOperation;

    @Override
    public void execute(@NonNull Long id) {
        final Company company = readCompanyOperation.execute(id);
        company.getContacts().forEach(contact -> contact.setCompany(null));
        companyRepository.save(company);
    }
}
