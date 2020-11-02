package com.vladislav.crm.communications.handlers.statuses.impl;

import com.vladislav.crm.communications.handlers.statuses.UpdateStatusRequestHandler;
import com.vladislav.crm.communications.requests.UpdateStatusRequest;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateStatusRequestHandlerImpl implements UpdateStatusRequestHandler {

    private final ReadOperation<Status> readStatusOperation;
    private final UpdateOperation<Status> statusUpdateOperation;

    @Override
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#requestPair.first)")
    public Status handle(Pair<Long, UpdateStatusRequest> requestPair) {
        final Long statusId = requestPair.getFirst();
        final UpdateStatusRequest request = requestPair.getSecond();

        final Status status = readStatusOperation.execute(statusId)
                .setName(request.getName());

        return statusUpdateOperation.execute(status);
    }
}
