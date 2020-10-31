package com.vladislav.crm.communications.grpc.adapters.leads.impl;

import com.proto.leads.ReadLeadInfoRequest;
import com.proto.leads.ReadLeadInfoResponse;
import com.vladislav.crm.communications.grpc.adapters.leads.ReadLeadInfoRequestHandlerAdapter;
import com.vladislav.crm.communications.handlers.leads.ReadLeadInfoRequestHandler;
import com.vladislav.crm.entities.Lead;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcReadLeadInfoRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadLeadInfoRequestHandlerAdapterImpl implements ReadLeadInfoRequestHandlerAdapter {

    private final ReadLeadInfoRequestHandler readLeadInfoRequestHandler;

    @Override
    public ReadLeadInfoResponse handle(ReadLeadInfoRequest request) {
        final Lead.LeadInfo info = readLeadInfoRequestHandler.handle(request.getLeadId());
        return ReadLeadInfoResponse.newBuilder()
                .setId(info.getId())
                .setName(info.getName())
                .setSale(info.getSale().toString())
                .build();
    }
}
