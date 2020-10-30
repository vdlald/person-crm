package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.DeleteOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteContactOperationImpl implements DeleteOperation<Contact> {

    private final EntityManager entityManager;

    // refactor candidate: может быть здесь лучше обойтись без нативных запросов ?
    @Override
    @Transactional
    public void execute(@NonNull Long id) {
        entityManager.flush();
        entityManager.clear();

        final Query query = entityManager.createNativeQuery(
                "DELETE FROM leads_contacts WHERE contact_id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

        final Query deleteContactQuery = entityManager.createNativeQuery(
                "DELETE FROM contacts WHERE contact_id = :id");
        deleteContactQuery.setParameter("id", id);
        deleteContactQuery.executeUpdate();
    }
}
