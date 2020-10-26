package com.vladislav.crm.authorizations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.leads.GetUserIdByLeadIdOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsLeadAuthorization implements UserOwnsEntityAuthorization {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final GetUserIdByLeadIdOperation getUserIdByLeadIdOperation;

    @Override
    public boolean hasAuthorization(Long leadId) {
        final User user = getCurrentUserStubOperation.execute();
        final long leadUserId = getUserIdByLeadIdOperation.execute(leadId);

        return user.getId().equals(leadUserId);
    }
}
