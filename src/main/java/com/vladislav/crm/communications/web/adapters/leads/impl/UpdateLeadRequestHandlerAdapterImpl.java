package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.vladislav.crm.communications.handlers.leads.UpdateLeadRequestHandler;
import com.vladislav.crm.communications.web.adapters.leads.UpdateLeadRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadLeadResponseAssembler;
import com.vladislav.crm.communications.web.requests.UpdateLeadRequest;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webUpdateLeadRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateLeadRequestHandlerAdapterImpl implements UpdateLeadRequestHandlerAdapter {

    private final UpdateLeadRequestHandler requestHandler;
    private final ReadLeadResponseAssembler readLeadResponseAssembler;

    @Override
    public EntityModel<ReadLeadResponse> handle(Pair<Long, UpdateLeadRequest> requestPair) {

        return readLeadResponseAssembler.toModel(
                requestHandler.handle(
                        Pair.of(requestPair.getFirst(), requestPair.getSecond().toCommunicationRequest())));
    }
}
