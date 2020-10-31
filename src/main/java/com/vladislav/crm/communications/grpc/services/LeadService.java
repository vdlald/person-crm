package com.vladislav.crm.communications.grpc.services;

import com.proto.leads.*;
import com.vladislav.crm.communications.grpc.DefaultStreamObserver;
import com.vladislav.crm.communications.grpc.GrpcServiceUtils;
import com.vladislav.crm.communications.grpc.adapters.leads.ReadLeadInfoForReportRequestHandlerAdapter;
import com.vladislav.crm.communications.grpc.adapters.leads.ReadLeadRequestHandlerAdapter;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LeadService extends LeadServiceGrpc.LeadServiceImplBase {

    private final ReadLeadRequestHandlerAdapter readLeadRequestHandlerAdapter;
    private final ReadLeadInfoForReportRequestHandlerAdapter readLeadInfoForReportRequestHandlerAdapter;

    @Override
    public void readLead(ReadLeadRequest request, StreamObserver<LeadResponse> responseObserver) {
        GrpcServiceUtils.handle(readLeadRequestHandlerAdapter, request, responseObserver);
    }

    @Override
    public StreamObserver<ReadLeadRequest> readLeads(StreamObserver<LeadResponse> responseObserver) {
        return DefaultStreamObserver.<ReadLeadRequest>newBuilder()
                .setOnNext(request -> responseObserver.onNext(readLeadRequestHandlerAdapter.handle(request)))
                .setOnCompleted(responseObserver::onCompleted)
                .build();
    }

    @Override
    public StreamObserver<ReadLeadInfoForReportRequest> readLeadInfoForReport(
            StreamObserver<ReadLeadInfoForReportResponse> responseObserver
    ) {
        return DefaultStreamObserver.<ReadLeadInfoForReportRequest>newBuilder()
                .setOnNext(req -> responseObserver.onNext(readLeadInfoForReportRequestHandlerAdapter.handle(req)))
                .setOnCompleted(responseObserver::onCompleted)
                .build();
    }
}
