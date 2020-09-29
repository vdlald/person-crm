package com.vladislav.crm.controllers.requesthandlers.impl;

import com.vladislav.crm.controllers.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.controllers.requesthandlers.UpdateContactRequestHandler;
import com.vladislav.crm.controllers.requests.UpdateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.companies.ReadCompanyOperation;
import com.vladislav.crm.services.operations.contacts.ReadContactOperation;
import com.vladislav.crm.services.operations.contacts.UpdateContactOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateContactRequestHandlerImpl implements UpdateContactRequestHandler {

    private final GetCurrentUserOperation getCurrentUserOperation;
    private final ReadContactOperation readContactOperation;
    private final ReadContactResponseAssembler readContactResponseAssembler;
    private final ReadCompanyOperation readCompanyOperation;
    private final UpdateContactOperation updateContactOperation;

    @Override
    public ResponseEntity<EntityModel<ReadContactResponse>> handle(Pair<Long, UpdateContactRequest> requestPair) {
        final User user = getCurrentUserOperation.execute();

        final Long contactId = requestPair.getFirst();
        final UpdateContactRequest request = requestPair.getSecond();

        final Contact contact = readContactOperation.execute(contactId);
        if (isUserOwner(user, contact)) {
            contact.setName(request.getName());

            final UpdateContactRequest.CompanyRequest companyRequest = request.getCompany();
            if (companyRequest == null) {
                contact.setCompany(null);
            } else {
                updateCompany(contact, companyRequest);
            }

            return ResponseEntity.ok(readContactResponseAssembler.toModel(updateContactOperation.execute(contact)));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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

    private boolean isUserOwner(User user, Contact contact) {
        return contact.getUser().getId().equals(user.getId());
    }
}
