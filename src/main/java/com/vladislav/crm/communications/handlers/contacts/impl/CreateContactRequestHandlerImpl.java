package com.vladislav.crm.communications.handlers.contacts.impl;

import com.vladislav.crm.communications.handlers.contacts.CreateContactRequestHandler;
import com.vladislav.crm.communications.requests.CreateContactRequest;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateContactRequestHandlerImpl implements CreateContactRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final CreateOperation<Contact> createContactOperation;
    private final ReadOperation<Company> readCompanyStubOperation;

    @Override
    @PreAuthorize("@userOwnsCompanyAuthorization.hasAuthorization(#request.companyId)")
    public Contact handle(CreateContactRequest request) {
        final User user = getCurrentUserStubOperation.execute();

        final Contact contact = new Contact()
                .setUserUnsafe(user)
                .setName(request.getName());

        final Long companyId = request.getCompanyId();
        if (companyId != null) {
            contact.setCompanyUnsafe(readCompanyStubOperation.execute(companyId));
        }

        return createContactOperation.execute(contact);
    }
}
