package com.vladislav.crm.services.operations.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.repositories.PipelineRepository;
import com.vladislav.crm.services.operations.pipelines.CreatePipelineOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreatePipelineOperationImpl implements CreatePipelineOperation {
    
    private final PipelineRepository pipelineRepository;
    
    @Override
    public Pipeline execute(Pipeline pipeline) {
        pipeline.setId(null);
        return pipelineRepository.save(pipeline);
    }
}
