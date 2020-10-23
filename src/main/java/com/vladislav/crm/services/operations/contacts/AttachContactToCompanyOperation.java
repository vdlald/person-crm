package com.vladislav.crm.services.operations.contacts;

public interface AttachContactToCompanyOperation {
    void execute(Long contactId, Long companyId);
}
