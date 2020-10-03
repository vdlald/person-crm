package com.vladislav.crm.controllers.authorizations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.statuses.GetUserIdByStatusIdOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsStatusAuthorization {

    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
    private final GetUserIdByStatusIdOperation getUserIdByStatusIdOperation;

    public boolean hasAuthorization(Long statusId) {
        final User user = getCurrentUserStubOperation.execute();
        final long statusUserId = getUserIdByStatusIdOperation.execute(statusId);

        return user.getId().equals(statusUserId);
    }
}
