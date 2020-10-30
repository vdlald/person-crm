package com.vladislav.crm.communications.grpc.services;

import com.proto.leads.LeadResponse;
import com.proto.leads.LeadServiceGrpc;
import com.proto.leads.ReadLeadRequest;
import com.vladislav.crm.communications.grpc.GrpcServiceUtils;
import com.vladislav.crm.communications.grpc.adapters.leads.ReadLeadRequestHandlerAdapter;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LeadService extends LeadServiceGrpc.LeadServiceImplBase {

    private final ReadLeadRequestHandlerAdapter readLeadRequestHandlerAdapter;

    @Override
    public void readLead(ReadLeadRequest request, StreamObserver<LeadResponse> responseObserver) {
        GrpcServiceUtils.handle(readLeadRequestHandlerAdapter, request, responseObserver);
    }
}
