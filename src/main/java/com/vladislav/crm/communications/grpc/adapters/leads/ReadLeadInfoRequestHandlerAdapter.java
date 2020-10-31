package com.vladislav.crm.communications.grpc.adapters.leads;

import com.proto.leads.ReadLeadInfoRequest;
import com.proto.leads.ReadLeadInfoResponse;
import com.vladislav.crm.communications.grpc.adapters.RequestHandlerAdapter;

public interface ReadLeadInfoRequestHandlerAdapter extends RequestHandlerAdapter<ReadLeadInfoRequest, ReadLeadInfoResponse> {
}
