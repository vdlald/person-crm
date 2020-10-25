package com.vladislav.crm.web.handlers.statuses.impl;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import com.vladislav.crm.web.assemblers.ReadStatusResponseAssembler;
import com.vladislav.crm.web.handlers.statuses.UpdateStatusRequestHandler;
import com.vladislav.crm.web.requests.UpdateStatusRequest;
import com.vladislav.crm.web.responses.ReadStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateStatusRequestHandlerImpl implements UpdateStatusRequestHandler {

    private final ReadOperation<Status> readStatusOperation;
    private final UpdateOperation<Status> statusUpdateOperation;
    private final ReadStatusResponseAssembler readStatusResponseAssembler;

    @Override
    public EntityModel<ReadStatusResponse> handle(Pair<Long, UpdateStatusRequest> requestPair) {
        final Long statusId = requestPair.getFirst();
        final UpdateStatusRequest request = requestPair.getSecond();

        final Status status = readStatusOperation.execute(statusId)
                .setName(request.getName());

        return readStatusResponseAssembler.toModel(statusUpdateOperation.execute(status));
    }
}
