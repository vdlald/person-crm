package com.vladislav.crm.communications.web.adapters.contacts.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.communications.web.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.communications.web.adapters.contacts.CreateContactRequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.CreateContactRequest;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateContactRequestHandlerAdapterImpl implements CreateContactRequestHandlerAdapter {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final ReadContactResponseAssembler readContactResponseAssembler;
    private final CreateOperation<Contact> createContactOperation;
    private final ReadOperation<Company> readCompanyStubOperation;

    @Override
    public EntityModel<ReadContactResponse> handle(CreateContactRequest request) {
        final User user = getCurrentUserStubOperation.execute();

        final Contact contact = new Contact()
                .setUserUnsafe(user)
                .setName(request.getName());

        final Long companyId = request.getCompanyId();
        if (companyId != null) {
            contact.setCompanyUnsafe(readCompanyStubOperation.execute(companyId));
        }

        return readContactResponseAssembler.toModel(createContactOperation.execute(contact));
    }
}
