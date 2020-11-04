package com.vladislav.crm.communications.handlers.leads.impl;

import com.vladislav.crm.communications.handlers.leads.ReadLeadInfoForReportRequestHandler;
import com.vladislav.crm.communications.requests.ReadLeadInfoForReportRequest;
import com.vladislav.crm.communications.responses.ReadLeadInfoForReportResponse;
import com.vladislav.crm.services.operations.leads.GetLeadNameOperation;
import com.vladislav.crm.services.operations.statuses.GetStatusNameOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadLeadInfoForReportRequestHandlerImpl implements ReadLeadInfoForReportRequestHandler {

    private final GetLeadNameOperation getLeadNameOperation;
    private final GetStatusNameOperation getStatusNameOperation;

    @Override
    @PreAuthorize("(@userOwnsLeadAuthorization.hasAuthorization(#request.leadId) && " +
            "@userOwnsStatusAuthorization.hasAuthorization(request.prevStatusId) && " +
            "@userOwnsStatusAuthorization.hasAuthorization(request.nextStatusId)) || " +
            "@userOwnsReadAllAuthorization.hasAuthorization()")
    public ReadLeadInfoForReportResponse handle(ReadLeadInfoForReportRequest request) {
        return new ReadLeadInfoForReportResponse()
                .setLeadName(getLeadNameOperation.execute(request.getLeadId()))
                .setPrevStatusName(getStatusNameOperation.execute(request.getPrevStatusId()))
                .setNextStatusName(getStatusNameOperation.execute(request.getNextStatusId()));
    }
}
