package com.vladislav.crm.communications.handlers.pipelines.impl;

import com.vladislav.crm.communications.handlers.pipelines.ReadPipelineStatusesRequestHandler;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.statuses.ReadPipelineStatusesOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadPipelineStatusesRequestHandlerImpl implements ReadPipelineStatusesRequestHandler {

    private final ReadPipelineStatusesOperation readPipelineStatusesOperation;

    @Override
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId) || " +
            "@userOwnsReadAllAuthorization.hasAuthorization()")
    public Collection<Status> handle(Long pipelineId) {
         return readPipelineStatusesOperation.execute(pipelineId);
    }
}
