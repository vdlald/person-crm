package com.vladislav.crm.controllers.authorizations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.leads.impl.GetUserIdByLeadIdOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsLeadAuthorization {

    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
    private final GetUserIdByLeadIdOperation getUserIdByLeadIdOperation;

    public boolean hasAuthorization(Long leadId) {
        final User user = getCurrentUserStubOperation.execute();
        final long leadUserId = getUserIdByLeadIdOperation.execute(leadId);

        return user.getId().equals(leadUserId);
    }
}
