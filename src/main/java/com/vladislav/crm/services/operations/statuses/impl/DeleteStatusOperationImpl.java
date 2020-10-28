package com.vladislav.crm.services.operations.statuses.impl;

import com.vladislav.crm.entities.Status;
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
public class DeleteStatusOperationImpl implements DeleteOperation<Status> {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void execute(@NonNull Long id) {
        entityManager.flush();
        entityManager.clear();

        final Query leadQuery = entityManager.createQuery(
                "update Lead lead set lead.status = null where lead.status.id = :id");
        leadQuery.setParameter("id", id);
        leadQuery.executeUpdate();

        final Query deleteQuery = entityManager.createQuery("delete from Status where id = :id");
        deleteQuery.setParameter("id", id);
        deleteQuery.executeUpdate();
    }
}
