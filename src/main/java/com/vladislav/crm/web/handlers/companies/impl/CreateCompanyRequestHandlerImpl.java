package com.vladislav.crm.web.handlers.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import com.vladislav.crm.web.assemblers.CompanyResponseAssembler;
import com.vladislav.crm.web.handlers.companies.CreateCompanyRequestHandler;
import com.vladislav.crm.web.requests.CreateCompanyRequest;
import com.vladislav.crm.web.responses.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateCompanyRequestHandlerImpl implements CreateCompanyRequestHandler {

    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
    private final CompanyResponseAssembler companyResponseAssembler;
    private final CreateOperation<Company> companyCreateOperation;

    @Override
    public EntityModel<CompanyResponse> handle(CreateCompanyRequest request) {
        final User user = getCurrentUserStubOperation.execute();

        final Company company = new Company()
                .setUser(user)
                .setName(request.getName());

        return companyResponseAssembler.toModel(companyCreateOperation.execute(company));
    }
}
