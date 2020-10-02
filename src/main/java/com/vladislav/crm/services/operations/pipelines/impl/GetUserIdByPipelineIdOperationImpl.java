package com.vladislav.crm.services.operations.pipelines.impl;

import com.vladislav.crm.repositories.PipelineRepository;
import com.vladislav.crm.services.operations.pipelines.GetUserIdByPipelineIdOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserIdByPipelineIdOperationImpl implements GetUserIdByPipelineIdOperation {

    private final PipelineRepository pipelineRepository;

    @Override
    public Long execute(Long pipelineId) {
        return pipelineRepository.findUserIdById(pipelineId).orElseThrow(EntityNotFoundException::new);
    }
}
