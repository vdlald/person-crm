package com.vladislav.crm.services.operations.statuses.impl;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.repositories.StatusRepository;
import com.vladislav.crm.services.operations.statuses.ReadPipelineStatusesOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadPipelineStatusesOperationImpl implements ReadPipelineStatusesOperation {

    private final StatusRepository statusRepository;

    @Override
    public Collection<Status> execute(Long pipelineId) {
        return statusRepository.findAllByPipelineId(pipelineId);
    }
}
