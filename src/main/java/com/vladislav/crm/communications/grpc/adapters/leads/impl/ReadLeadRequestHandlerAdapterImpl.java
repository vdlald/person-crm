package com.vladislav.crm.communications.grpc.adapters.leads.impl;

import com.proto.leads.LeadResponse;
import com.proto.leads.ReadLeadRequest;
import com.vladislav.crm.communications.grpc.adapters.leads.ReadLeadRequestHandlerAdapter;
import com.vladislav.crm.communications.grpc.assemblers.LeadResponseAssembler;
import com.vladislav.crm.communications.handlers.leads.ReadLeadRequestHandler;
import com.vladislav.crm.entities.Lead;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("grpcReadLeadRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadLeadRequestHandlerAdapterImpl implements ReadLeadRequestHandlerAdapter {

    private final ReadLeadRequestHandler requestHandler;
    private final LeadResponseAssembler leadResponseAssembler;

    @Override
    public LeadResponse handle(ReadLeadRequest request) {
        final Lead lead = requestHandler.handle(request.getLeadId());
        return leadResponseAssembler.toMessage(lead);
    }
}
