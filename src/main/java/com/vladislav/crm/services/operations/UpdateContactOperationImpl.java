package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateContactOperationImpl implements UpdateContactOperation {

    private final ContactRepository contactRepository;

    @Override
    public Contact execute(Contact contact) {
        if (contactRepository.existsById(contact.getId())) {
            return contactRepository.save(contact);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
