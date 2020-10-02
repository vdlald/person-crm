package com.vladislav.crm.controllers.requesthandlers.pipelines.impl;

import com.vladislav.crm.controllers.requesthandlers.pipelines.DeletePipelineRequestHandler;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.DeleteOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeletePipelineRequestHandlerImpl implements DeletePipelineRequestHandler {

    private final DeleteOperation<Pipeline> pipelineDeleteOperation;

    @Override
    public ResponseEntity<Void> handle(Long pipelineId) {
        pipelineDeleteOperation.execute(pipelineId);
        return ResponseEntity.noContent().build();
    }
}
