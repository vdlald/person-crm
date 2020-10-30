package com.vladislav.crm.communications.handlers.pipelines.impl;

import com.vladislav.crm.communications.handlers.pipelines.ReadUserPipelinesRequestHandler;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.pipelines.ReadUserPipelinesOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserPipelinesRequestHandlerImpl implements ReadUserPipelinesRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final ReadUserPipelinesOperation readUserPipelinesOperation;

    @Override
    public Collection<Pipeline> handle(Void unused) {
        final User user = getCurrentUserStubOperation.execute();
        return readUserPipelinesOperation.execute(user.getId());
    }

    @Override
    public Collection<Pipeline> handle() {
        return handle(null);
    }
}
