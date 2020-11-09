package com.vladislav.crm.communications.handlers.companies.impl;

import com.vladislav.crm.communications.handlers.companies.CreateCompanyRequestHandler;
import com.vladislav.crm.communications.requests.CreateCompanyRequest;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateCompanyRequestHandlerImpl implements CreateCompanyRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final CreateOperation<Company> companyCreateOperation;

    @Override
    public Company handle(CreateCompanyRequest request) {
        final User user = getCurrentUserStubOperation.execute();

        final Company company = new Company()
                .setUser(user)
                .setName(request.getName());

        return companyCreateOperation.execute(company);
    }
}
