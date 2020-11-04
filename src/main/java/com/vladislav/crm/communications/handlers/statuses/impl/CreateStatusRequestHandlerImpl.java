package com.vladislav.crm.communications.handlers.statuses.impl;

import com.vladislav.crm.communications.handlers.statuses.CreateStatusRequestHandler;
import com.vladislav.crm.communications.requests.CreateStatusRequest;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateStatusRequestHandlerImpl implements CreateStatusRequestHandler {

    private final CreateOperation<Status> statusCreateOperation;
    private final ReadOperation<Pipeline> readPipelineOperation;

    @Override
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#request.pipelineId)")
    public Status handle(CreateStatusRequest request) {
        final Status status = new Status()
                .setName(request.getName())
                .setPipelineUnsafe(readPipelineOperation.execute(request.getPipelineId()));

        return statusCreateOperation.execute(status);
    }
}
