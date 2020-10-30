package com.vladislav.crm.communications.web.adapters.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import com.vladislav.crm.communications.web.assemblers.CompanyResponseAssembler;
import com.vladislav.crm.communications.web.adapters.companies.UpdateCompanyRequestHandler;
import com.vladislav.crm.communications.web.requests.UpdateCompanyRequest;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateCompanyRequestHandlerImpl implements UpdateCompanyRequestHandler {

    private final ReadOperation<Company> readCompanyOperation;
    private final CompanyResponseAssembler companyResponseAssembler;
    private final UpdateOperation<Company> companyUpdateOperation;

    @Override
    public EntityModel<CompanyResponse> handle(Pair<Long, UpdateCompanyRequest> requestPair) {
        final Long companyId = requestPair.getFirst();
        final UpdateCompanyRequest request = requestPair.getSecond();

        final Company company = readCompanyOperation.execute(companyId)
                .setName(request.getName());

        return companyResponseAssembler.toModel(companyUpdateOperation.execute(company));
    }
}
