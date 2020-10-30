package com.vladislav.crm.communications.web.handlers.contacts.impl;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import com.vladislav.crm.communications.web.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.communications.web.handlers.contacts.UpdateContactRequestHandler;
import com.vladislav.crm.communications.web.requests.UpdateContactRequest;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateContactRequestHandlerImpl implements UpdateContactRequestHandler {

    private final ReadOperation<Contact> readContactOperation;
    private final ReadContactResponseAssembler readContactResponseAssembler;
    private final UpdateOperation<Contact> updateContactOperation;

    @Override
    public EntityModel<ReadContactResponse> handle(Pair<Long, UpdateContactRequest> requestPair) {
        final Long contactId = requestPair.getFirst();
        final UpdateContactRequest request = requestPair.getSecond();

        final Contact contact = readContactOperation.execute(contactId)
                .setName(request.getName());

        return readContactResponseAssembler.toModel(updateContactOperation.execute(contact));
    }
}
