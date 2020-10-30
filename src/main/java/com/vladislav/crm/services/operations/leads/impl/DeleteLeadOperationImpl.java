package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.entities.Lead;
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
public class DeleteLeadOperationImpl implements DeleteOperation<Lead> {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void execute(@NonNull Long id) {
        entityManager.flush();
        entityManager.clear();

        final Query query = entityManager.createNativeQuery(
                "DELETE FROM leads_contacts WHERE lead_id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

        final Query deleteContactQuery = entityManager.createNativeQuery(
                "DELETE FROM leads WHERE lead_id = :id");
        deleteContactQuery.setParameter("id", id);
        deleteContactQuery.executeUpdate();
    }
}
