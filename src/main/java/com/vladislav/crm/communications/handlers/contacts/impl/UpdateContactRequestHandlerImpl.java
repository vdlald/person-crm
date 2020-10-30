package com.vladislav.crm.communications.handlers.contacts.impl;

import com.vladislav.crm.communications.handlers.contacts.UpdateContactRequestHandler;
import com.vladislav.crm.communications.requests.UpdateContactRequest;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateContactRequestHandlerImpl implements UpdateContactRequestHandler {

    private final ReadOperation<Contact> readContactOperation;
    private final UpdateOperation<Contact> updateContactOperation;

    @Override
    public Contact handle(Pair<Long, UpdateContactRequest> requestPair) {
        final Long contactId = requestPair.getFirst();
        final UpdateContactRequest request = requestPair.getSecond();

        final Contact contact = readContactOperation.execute(contactId)
                .setName(request.getName());

        return updateContactOperation.execute(contact);
    }
}
