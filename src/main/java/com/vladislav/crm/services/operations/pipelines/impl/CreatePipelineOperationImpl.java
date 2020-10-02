package com.vladislav.crm.services.operations.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.abstractions.AbstractCreateOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePipelineOperationImpl extends AbstractCreateOperation<Pipeline> {
    @Autowired
    public CreatePipelineOperationImpl(JpaRepository<Pipeline, Long> repository) {
        super(repository);
    }
}
