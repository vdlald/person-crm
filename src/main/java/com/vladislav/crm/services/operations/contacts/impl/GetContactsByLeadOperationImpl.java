package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.contacts.GetContactsByLeadOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetContactsByLeadOperationImpl implements GetContactsByLeadOperation {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public List<Contact> execute(Lead lead) {
        return entityManager.merge(lead).getContacts();
    }
}
