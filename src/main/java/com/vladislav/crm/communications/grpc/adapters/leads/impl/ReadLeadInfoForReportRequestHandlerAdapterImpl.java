package com.vladislav.crm.communications.grpc.adapters.leads.impl;

import com.proto.leads.ReadLeadInfoForReportRequest;
import com.proto.leads.ReadLeadInfoForReportResponse;
import com.vladislav.crm.communications.grpc.adapters.leads.ReadLeadInfoForReportRequestHandlerAdapter;
import com.vladislav.crm.communications.handlers.leads.ReadLeadInfoForReportRequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcReadLeadInfoForReportRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadLeadInfoForReportRequestHandlerAdapterImpl implements ReadLeadInfoForReportRequestHandlerAdapter {

    private final ReadLeadInfoForReportRequestHandler requestHandler;

    @Override
    public ReadLeadInfoForReportResponse handle(ReadLeadInfoForReportRequest request) {
        val response = requestHandler.handle(
                new com.vladislav.crm.communications.requests.ReadLeadInfoForReportRequest()
                        .setLeadId(request.getLeadId())
                        .setNextStatusId(request.getNextStatusId())
                        .setPrevStatusId(request.getPrevStatusId()));

        return ReadLeadInfoForReportResponse.newBuilder()
                .setLeadName(response.getLeadName())
                .setPrevStatusName(response.getPrevStatusName())
                .setNextStatusName(response.getNextStatusName())
                .build();
    }
}
