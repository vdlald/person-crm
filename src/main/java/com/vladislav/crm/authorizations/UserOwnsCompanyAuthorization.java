package com.vladislav.crm.authorizations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.companies.GetUserIdByCompanyIdOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsCompanyAuthorization {

    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
    private final GetUserIdByCompanyIdOperation getUserIdByCompanyIdOperation;

    public boolean hasAuthorization(Long contactId) {
        final User user = getCurrentUserStubOperation.execute();
        final long contactUserId = getUserIdByCompanyIdOperation.execute(contactId);

        return user.getId().equals(contactUserId);
    }
}
