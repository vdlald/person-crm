package com.vladislav.crm.services.operations.contacts.impl;

import com.vladislav.crm.repositories.ContactRepository;
import com.vladislav.crm.services.operations.contacts.AttachContactToCompanyOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachContactToCompanyOperationImpl implements AttachContactToCompanyOperation {

    private final ContactRepository contactRepository;

    @Override
    public void execute(
            @NonNull Long contactId,
            @NonNull Long companyId
    ) {
        contactRepository.attachContactToCompany(contactId, companyId);
    }
}
