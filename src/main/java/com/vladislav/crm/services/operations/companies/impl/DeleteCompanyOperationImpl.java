package com.vladislav.crm.services.operations.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.repositories.CompanyRepository;
import com.vladislav.crm.services.operations.DeleteOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteCompanyOperationImpl implements DeleteOperation<Company> {

    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Override
    public void execute(@NonNull Long id) {
        remove(companyRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    @Transactional
    public void execute(Company entity) {
        remove(entityManager.merge(entity));
    }

    private void remove(Company persistedCompany) {
        persistedCompany.getContacts().forEach(contact -> contact.setCompanyUnsafe(null));
        companyRepository.save(persistedCompany);
    }
}
