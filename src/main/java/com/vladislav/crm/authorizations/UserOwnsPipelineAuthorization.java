package com.vladislav.crm.authorizations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.pipelines.GetUserIdByPipelineIdOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOwnsPipelineAuthorization implements UserOwnsEntityAuthorization{

    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
    private final GetUserIdByPipelineIdOperation getUserIdByPipelineIdOperation;

    @Override
    public boolean hasAuthorization(Long pipelineId) {
        final User user = getCurrentUserStubOperation.execute();
        final long contactUserId = getUserIdByPipelineIdOperation.execute(pipelineId);

        return user.getId().equals(contactUserId);
    }
}
