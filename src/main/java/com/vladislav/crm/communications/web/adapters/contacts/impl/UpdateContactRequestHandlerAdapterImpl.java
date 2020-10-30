package com.vladislav.crm.communications.web.adapters.contacts.impl;

import com.vladislav.crm.communications.handlers.contacts.UpdateContactRequestHandler;
import com.vladislav.crm.communications.web.adapters.contacts.UpdateContactRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.communications.web.requests.UpdateContactRequest;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webUpdateContactRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateContactRequestHandlerAdapterImpl implements UpdateContactRequestHandlerAdapter {

    private final UpdateContactRequestHandler requestHandler;
    private final ReadContactResponseAssembler readContactResponseAssembler;

    @Override
    public EntityModel<ReadContactResponse> handle(Pair<Long, UpdateContactRequest> requestPair) {
        return readContactResponseAssembler.toModel(
                requestHandler.handle(
                        Pair.of(requestPair.getFirst(), requestPair.getSecond().toCommunicationRequest())));
    }
}
