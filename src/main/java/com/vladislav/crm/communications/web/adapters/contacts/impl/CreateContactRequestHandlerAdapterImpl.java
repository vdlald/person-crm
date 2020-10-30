package com.vladislav.crm.communications.web.adapters.contacts.impl;

import com.vladislav.crm.communications.handlers.contacts.CreateContactRequestHandler;
import com.vladislav.crm.communications.web.adapters.contacts.CreateContactRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.communications.web.requests.CreateContactRequest;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateContactRequestHandlerAdapterImpl implements CreateContactRequestHandlerAdapter {

    private final ReadContactResponseAssembler readContactResponseAssembler;
    private final CreateContactRequestHandler requestHandler;

    @Override
    public EntityModel<ReadContactResponse> handle(CreateContactRequest request) {
        return readContactResponseAssembler.toModel(requestHandler.handle(request.toCommunicationRequest()));
    }
}
