package com.vladislav.crm.communications.handlers.contacts.impl;

import com.vladislav.crm.communications.handlers.contacts.ReadUserContactsRequestHandler;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.contacts.ReadUserContactsOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserContactsRequestHandlerImpl implements ReadUserContactsRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final ReadUserContactsOperation readUserContactsOperation;

    @Override
    public Collection<Contact> handle(Void unused) {
        final User user = getCurrentUserStubOperation.execute();
        return readUserContactsOperation.execute(user.getId());
    }

    @Override
    public Collection<Contact> handle() {
        return handle(null);
    }
}
