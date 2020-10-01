package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.abstractions.AbstractReadOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReadContactOperationImpl extends AbstractReadOperation<Contact> {
    @Autowired
    public ReadContactOperationImpl(JpaRepository<Contact, Long> repository) {
        super(repository);
    }
}
