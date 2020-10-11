package com.vladislav.crm.authorizations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.companies.GetUserIdByCompanyIdOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsCompanyAuthorization implements UserOwnsEntityAuthorization {

    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
    private final GetUserIdByCompanyIdOperation getUserIdByCompanyIdOperation;

    @Override
    public boolean hasAuthorization(Long companyId) {
        final User user = getCurrentUserStubOperation.execute();
        final long companyUserId = getUserIdByCompanyIdOperation.execute(companyId);

        return user.getId().equals(companyUserId);
    }
}
