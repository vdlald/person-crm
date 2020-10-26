package com.vladislav.crm.services.operations.statuses.impl;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service("readStatusStubOperation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadStatusStubOperationImpl implements ReadOperation<Status> {

    private final EntityManager entityManager;

    @Override
    public Status execute(Long id) {
        return entityManager.getReference(Status.class, id);
    }
}
