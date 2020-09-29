package com.vladislav.crm.controllers.authorizations;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.contacts.ReadContactOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsContactAuthorization {

    private final GetCurrentUserOperation getCurrentUserOperation;
    private final ReadContactOperation readContactOperation;

    public boolean hasAuthorization(Long contactId) {
        final Contact contact = readContactOperation.execute(contactId);
        final User user = getCurrentUserOperation.execute();

        return contact.getUser().getId().equals(user.getId());
    }
}
