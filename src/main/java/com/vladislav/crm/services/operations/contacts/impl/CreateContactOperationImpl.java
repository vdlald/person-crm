package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.abstractions.AbstractCreateOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateContactOperationImpl extends AbstractCreateOperation<Contact> {
    @Autowired
    public CreateContactOperationImpl(JpaRepository<Contact, Long> repository) {
        super(repository);
    }
}
