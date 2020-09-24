package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserContactsOperationImpl implements ReadUserContactsOperation {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> execute(Long userId) {
        return contactRepository.findAllByUserId(userId);
    }
}
