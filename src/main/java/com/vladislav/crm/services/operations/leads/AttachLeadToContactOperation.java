package com.vladislav.crm.services.operations.leads;

public interface AttachLeadToContactOperation {
    void execute(Long leadId, Long contactId);
}
