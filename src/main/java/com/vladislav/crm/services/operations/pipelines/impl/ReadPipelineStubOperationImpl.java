package com.vladislav.crm.services.operations.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service("readPipelineStubOperation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadPipelineStubOperationImpl implements ReadOperation<Pipeline> {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Pipeline execute(@NonNull Long id) {
        return entityManager.getReference(Pipeline.class, id);
    }
}
