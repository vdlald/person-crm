package com.vladislav.crm.controllers.requesthandlers.contacts.impl;

import com.vladislav.crm.controllers.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.controllers.requesthandlers.contacts.UpdateContactRequestHandler;
import com.vladislav.crm.controllers.requests.UpdateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.contacts.UpdateContactOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateContactRequestHandlerImpl implements UpdateContactRequestHandler {

    private final ReadOperation<Contact> readContactOperation;
    private final ReadContactResponseAssembler readContactResponseAssembler;
    private final ReadOperation<Company> readCompanyOperation;
    private final UpdateContactOperation updateContactOperation;

    @Override
    public EntityModel<ReadContactResponse> handle(Pair<Long, UpdateContactRequest> requestPair) {
        final Long contactId = requestPair.getFirst();
        final UpdateContactRequest request = requestPair.getSecond();

        final Contact contact = readContactOperation.execute(contactId)
                .setName(request.getName());

        final UpdateContactRequest.CompanyRequest companyRequest = request.getCompany();
        if (companyRequest == null) {
            contact.setCompany(null);
        } else {
            updateCompany(contact, companyRequest);
        }

        return readContactResponseAssembler.toModel(updateContactOperation.execute(contact));
    }

    private void updateCompany(Contact contact, UpdateContactRequest.CompanyRequest companyRequest) {
        final Long companyId = companyRequest.getId();
        final String companyName = companyRequest.getName();

        if (companyId != null) {
            if (contact.getCompany() == null || !contact.getCompany().getId().equals(companyId)) {
                contact.setCompany(readCompanyOperation.execute(companyId));
            }
        } else if (companyName != null) {
            if (contact.getCompany() == null) {
                contact.setCompany(new Company().setName(companyName));
            } else {
                contact.getCompany().setName(companyName);
            }
        }
    }
}
