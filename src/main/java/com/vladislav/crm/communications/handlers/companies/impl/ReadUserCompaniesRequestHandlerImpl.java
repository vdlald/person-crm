package com.vladislav.crm.communications.handlers.companies.impl;

import com.vladislav.crm.communications.handlers.companies.ReadUserCompaniesRequestHandler;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.companies.ReadUserCompaniesOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserCompaniesRequestHandlerImpl implements ReadUserCompaniesRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final ReadUserCompaniesOperation readUserCompaniesOperation;

    @Override
    public Collection<Company> handle(Void unused) {
        final User user = getCurrentUserStubOperation.execute();

        return readUserCompaniesOperation.execute(user.getId());
    }

    @Override
    public Collection<Company> handle() {
        return handle(null);
    }
}
