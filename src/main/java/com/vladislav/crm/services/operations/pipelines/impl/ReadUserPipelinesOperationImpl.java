package com.vladislav.crm.services.operations.pipelines.impl;


import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.repositories.PipelineRepository;
import com.vladislav.crm.services.operations.pipelines.ReadUserPipelinesOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserPipelinesOperationImpl implements ReadUserPipelinesOperation {

    private final PipelineRepository pipelineRepository;

    @Override
    public Collection<Pipeline> execute(@NonNull Long userId) {
        return pipelineRepository.findAllByUserId(userId);
    }
}
