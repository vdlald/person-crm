package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.repositories.CompanyRepository;
import com.vladislav.crm.repositories.ContactRepository;
import com.vladislav.crm.services.operations.contacts.DeleteContactOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteContactOperationImpl implements DeleteContactOperation {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;  // refactor candidate

    @Override
    public void execute(Long id) {
        final Contact contact = contactRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        contact.getLeads().forEach(contact::removeLead);
        contact.setUser(null);

        final Company company = contact.getCompany();
        if (company != null)
            if (company.getContacts().isEmpty())
                companyRepository.delete(company);
            else
                contact.setCompany(null);

        contactRepository.delete(contact);
    }
}
