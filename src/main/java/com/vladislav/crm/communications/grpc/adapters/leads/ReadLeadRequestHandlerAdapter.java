package com.vladislav.crm.communications.grpc.adapters.leads;

import com.proto.leads.LeadResponse;
import com.proto.leads.ReadLeadRequest;
import com.vladislav.crm.communications.grpc.adapters.RequestHandlerAdapter;

public interface ReadLeadRequestHandlerAdapter extends RequestHandlerAdapter<ReadLeadRequest, LeadResponse> {
}
