package com.vladislav.crm.controllers.requesthandlers.contacts.impl;

import com.vladislav.crm.controllers.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.controllers.requesthandlers.contacts.CreateContactRequestHandler;
import com.vladislav.crm.controllers.requests.CreateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.contacts.CreateContactOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateContactRequestHandlerImpl implements CreateContactRequestHandler {

    private final GetCurrentUserOperation getCurrentUserOperation;
    private final ReadContactResponseAssembler readContactResponseAssembler;
    private final CreateContactOperation createContactOperation;
    private final ReadOperation<Company> readCompanyOperation;

    @Override
    public EntityModel<ReadContactResponse> handle(CreateContactRequest request) {
        final User user = getCurrentUserOperation.execute();

        final Contact contact = new Contact()
                .setUser(user)
                .setName(request.getName());

        final CreateContactRequest.CompanyRequest companyRequest = request.getCompany();
        if (companyRequest != null) {
            if (companyRequest.getId() != null) {
                final Company company = readCompanyOperation.execute(companyRequest.getId());
                contact.setCompany(company);
            } else {
                final String companyRequestName = companyRequest.getName();
                if (!companyRequestName.isEmpty() && !companyRequestName.isBlank()) {
                    final Company company = new Company().setName(companyRequestName);
                    contact.setCompany(company);
                }
            }
        }

        return readContactResponseAssembler.toModel(createContactOperation.execute(contact));
    }
}
