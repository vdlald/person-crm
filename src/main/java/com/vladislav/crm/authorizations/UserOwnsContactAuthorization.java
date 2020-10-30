package com.vladislav.crm.authorizations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.contacts.GetUserIdByContactIdOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsContactAuthorization implements UserOwnsEntityAuthorization {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final GetUserIdByContactIdOperation getUserIdByContactIdOperation;

    @Override
    public boolean hasAuthorization(Long contactId) {
        final User user = getCurrentUserStubOperation.execute();
        final long contactUserId = getUserIdByContactIdOperation.execute(contactId);

        return user.getId().equals(contactUserId);
    }
}
