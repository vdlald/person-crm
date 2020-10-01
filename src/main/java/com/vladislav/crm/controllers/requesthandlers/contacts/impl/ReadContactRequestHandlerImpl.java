package com.vladislav.crm.controllers.requesthandlers.contacts.impl;

import com.vladislav.crm.controllers.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.controllers.requesthandlers.contacts.ReadContactRequestHandler;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadContactRequestHandlerImpl implements ReadContactRequestHandler {
    
    private final ReadContactResponseAssembler readContactResponseAssembler;
    private final ReadOperation<Contact> readContactOperation;

    @Override
    public EntityModel<ReadContactResponse> handle(Long contactId) {
        return readContactResponseAssembler.toModel(readContactOperation.execute(contactId));
    }
}
