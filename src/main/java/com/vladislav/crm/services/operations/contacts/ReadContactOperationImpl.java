package com.vladislav.crm.services.operations.contacts;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadContactOperationImpl implements ReadContactOperation {

    private final ContactRepository contactRepository;

    @Override
    public Contact execute(Long contactId) {
        return contactRepository.findById(contactId).orElseThrow(EntityNotFoundException::new);
    }
}
