package com.vladislav.crm.communications.web.adapters.statuses.impl;

import com.vladislav.crm.communications.handlers.statuses.UpdateStatusRequestHandler;
import com.vladislav.crm.communications.web.adapters.statuses.UpdateStatusRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadStatusResponseAssembler;
import com.vladislav.crm.communications.web.requests.UpdateStatusRequest;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import com.vladislav.crm.entities.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webUpdateStatusRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateStatusRequestHandlerAdapterImpl implements UpdateStatusRequestHandlerAdapter {

    private final UpdateStatusRequestHandler requestHandler;
    private final ReadStatusResponseAssembler readStatusResponseAssembler;

    @Override
    public EntityModel<ReadStatusResponse> handle(Pair<Long, UpdateStatusRequest> requestPair) {
        final Status status = requestHandler.handle(
                Pair.of(requestPair.getFirst(), requestPair.getSecond().toCommunicationRequest())
        );
        return readStatusResponseAssembler.toModel(status);
    }
}
