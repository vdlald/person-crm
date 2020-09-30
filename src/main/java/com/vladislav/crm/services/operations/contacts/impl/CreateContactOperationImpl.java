package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.repositories.ContactRepository;
import com.vladislav.crm.services.operations.contacts.CreateContactOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateContactOperationImpl implements CreateContactOperation {

    private final ContactRepository contactRepository;

    @Override
    public Contact execute(Contact contact) {
        contact.setId(null);
        return contactRepository.save(contact);
    }
}
