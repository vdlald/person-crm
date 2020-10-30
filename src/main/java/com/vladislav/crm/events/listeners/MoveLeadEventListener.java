package com.vladislav.crm.events.listeners;

import com.proto.report.AddMoveLeadLogRequest;
import com.proto.report.ReportServiceGrpc.ReportServiceStub;
import com.vladislav.crm.events.MoveLeadEvent;
import com.vladislav.crm.communications.grpc.DefaultStreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoveLeadEventListener {

    private final ReportServiceStub reportService;

    @Async
    @EventListener
    public void onApplicationEvent(MoveLeadEvent event) {
        final AddMoveLeadLogRequest request = AddMoveLeadLogRequest.newBuilder()
                .setLeadId(event.getLeadId())
                .setUserId(event.getLeadId())
                .setPrevStatusId(event.getPrevStatusId())
                .setNextStatusId(event.getNextStatusId())
                .build();
        reportService.addMoveLeadLog(request, new DefaultStreamObserver<>());
    }
}
