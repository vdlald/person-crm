package com.vladislav.crm.services.operations.pipelines.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.repositories.PipelineRepository;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeletePipelineOperationImpl implements DeleteOperation<Pipeline> {

    private final PipelineRepository pipelineRepository;
    private final ReadOperation<Pipeline> readPipelineOperation;
    private final DeleteOperation<Status> statusDeleteOperation;

    @Override
    public void execute(@NonNull Long id) {
        final Pipeline pipeline = readPipelineOperation.execute(id);
        pipeline.getStatuses().forEach(statusDeleteOperation::execute);
        pipelineRepository.delete(pipeline);
    }
}
