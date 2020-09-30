package com.vladislav.crm.controllers.authorizations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.contacts.GetUserIdByContactIdOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsContactAuthorization {

    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
    private final GetUserIdByContactIdOperation getUserIdByContactIdOperation;

    public boolean hasAuthorization(Long contactId) {
        final User user = getCurrentUserStubOperation.execute();
        final long contactUserId = getUserIdByContactIdOperation.execute(contactId);

        return user.getId().equals(contactUserId);
    }
}
