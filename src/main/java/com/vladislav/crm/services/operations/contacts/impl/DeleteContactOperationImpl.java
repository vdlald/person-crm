package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.repositories.ContactRepository;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.services.operations.abstractions.AbstractDeleteOperation;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DeleteContactOperationImpl extends AbstractDeleteOperation<Contact> {

    private final ContactRepository contactRepository;
    private final DeleteOperation<Company> companyDeleteOperation;

    public DeleteContactOperationImpl(ContactRepository repository, DeleteOperation<Company> companyDeleteOperation) {
        super(repository);
        this.companyDeleteOperation = companyDeleteOperation;
        contactRepository = repository;
    }

    @Override
    public void execute(Long id) {
        final Contact contact = contactRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        contact.getLeads().forEach(contact::removeLead);
        contact.setUser(null);

        final Company company = contact.getCompany();
        if (company != null) {
            if (company.getContacts().isEmpty()) {
                companyDeleteOperation.execute(company);
            } else {
                contact.setCompany(null);
            }
        }

        contactRepository.delete(contact);
    }
}
