package com.vladislav.crm.authorizations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsReadAllAuthorization {

    private final GetCurrentUserOperation getCurrentUserOperation;

    public boolean hasAuthorization() {
        final User user = getCurrentUserOperation.execute();
        return user.getAuthorities().contains(User.Authority.READ_ALL);
    }
}
