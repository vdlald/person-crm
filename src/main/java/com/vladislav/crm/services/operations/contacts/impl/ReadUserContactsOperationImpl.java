package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.repositories.ContactRepository;
import com.vladislav.crm.services.operations.contacts.ReadUserContactsOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserContactsOperationImpl implements ReadUserContactsOperation {

    private final ContactRepository contactRepository;

    @Override
    public Collection<Contact> execute(Long userId) {
        return contactRepository.findAllByUserId(userId);
    }
}
