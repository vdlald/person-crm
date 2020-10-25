package com.vladislav.crm.authorizations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.statuses.GetUserIdByStatusIdOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsStatusAuthorization implements UserOwnsEntityAuthorization {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final GetUserIdByStatusIdOperation getUserIdByStatusIdOperation;

    @Override
    public boolean hasAuthorization(Long statusId) {
        final User user = getCurrentUserStubOperation.execute();
        final long statusUserId = getUserIdByStatusIdOperation.execute(statusId);

        return user.getId().equals(statusUserId);
    }
}
