package com.vladislav.crm.communications.web.handlers.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.communications.web.assemblers.CompanyResponseAssembler;
import com.vladislav.crm.communications.web.handlers.companies.CreateCompanyRequestHandler;
import com.vladislav.crm.communications.web.requests.CreateCompanyRequest;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateCompanyRequestHandlerImpl implements CreateCompanyRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final CompanyResponseAssembler companyResponseAssembler;
    private final CreateOperation<Company> companyCreateOperation;

    @Override
    public EntityModel<CompanyResponse> handle(CreateCompanyRequest request) {
        final User user = getCurrentUserStubOperation.execute();

        final Company company = new Company()
                .setUserUnsafe(user)
                .setName(request.getName());

        return companyResponseAssembler.toModel(companyCreateOperation.execute(company));
    }
}
