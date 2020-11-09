package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.repositories.ContactRepository;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteContactOperationImpl implements DeleteOperation<Contact> {

    private final ContactRepository contactRepository;
    private final ReadOperation<Contact> readContactOperation;

    @Override
    public void execute(@NonNull Long id) {
        final Contact contact = readContactOperation.execute(id);
        contact.getLeads().forEach(lead -> lead.removeContact(contact));
        contactRepository.delete(contact);
    }
}
