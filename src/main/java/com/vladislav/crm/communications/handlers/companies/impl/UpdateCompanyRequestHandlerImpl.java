package com.vladislav.crm.communications.handlers.companies.impl;

import com.vladislav.crm.communications.handlers.companies.UpdateCompanyRequestHandler;
import com.vladislav.crm.communications.requests.UpdateCompanyRequest;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateCompanyRequestHandlerImpl implements UpdateCompanyRequestHandler {

    private final ReadOperation<Company> readCompanyOperation;
    private final UpdateOperation<Company> companyUpdateOperation;

    @Override
    public Company handle(Pair<Long, UpdateCompanyRequest> requestPair) {
        final Long companyId = requestPair.getFirst();
        final UpdateCompanyRequest request = requestPair.getSecond();

        final Company company = readCompanyOperation.execute(companyId)
                .setName(request.getName());

        return companyUpdateOperation.execute(company);
    }
}
