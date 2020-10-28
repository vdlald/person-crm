package com.vladislav.crm.services.operations.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.DeleteOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeletePipelineOperationImpl implements DeleteOperation<Pipeline> {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void execute(Long id) {
        entityManager.flush();
        entityManager.clear();

        final Query leadQuery = entityManager.createQuery(
                "update Lead lead set lead.status = null where lead.status.id in " +
                        "(select id from Status where pipeline.id = :id)");
        leadQuery.setParameter("id", id);
        leadQuery.executeUpdate();

        final Query deleteStatuses = entityManager.createNativeQuery(
                "DELETE FROM statuses WHERE pipeline_id = :id");
        deleteStatuses.setParameter("id", id);
        deleteStatuses.executeUpdate();

        final Query deletePipeline = entityManager.createNativeQuery(
                "DELETE FROM pipelines WHERE pipeline_id = :id");
        deletePipeline.setParameter("id", id);
        deletePipeline.executeUpdate();
    }
}
