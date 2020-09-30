package com.vladislav.crm.services.operations.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.repositories.PipelineRepository;
import com.vladislav.crm.services.operations.pipelines.ReadPipelineOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadPipelineOperationImpl implements ReadPipelineOperation {

    private final PipelineRepository pipelineRepository;

    @Override
    public Pipeline execute(Long pipelineId) {
        return pipelineRepository.findById(pipelineId).orElseThrow(EntityNotFoundException::new);
    }
}
