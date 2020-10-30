package com.vladislav.crm.services.operations.leads;

public interface MoveLeadToStatusOperation {
    void execute(Long leadId, Long statusId);
}
