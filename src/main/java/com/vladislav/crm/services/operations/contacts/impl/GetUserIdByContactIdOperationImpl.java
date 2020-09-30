package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.repositories.ContactRepository;
import com.vladislav.crm.services.operations.contacts.GetUserIdByContactIdOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserIdByContactIdOperationImpl implements GetUserIdByContactIdOperation {

    private final ContactRepository contactRepository;

    @Override
    public long execute(long contactId) {
        return contactRepository.findUserIdById(contactId).orElseThrow(EntityNotFoundException::new);
    }
}
