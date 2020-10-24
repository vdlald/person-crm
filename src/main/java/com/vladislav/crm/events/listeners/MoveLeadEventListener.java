package com.vladislav.crm.events.listeners;

import com.proto.report.AddMoveLeadLogRequest;
import com.proto.report.ReportServiceGrpc.ReportServiceStub;
import com.vladislav.crm.events.MoveLeadEvent;
import com.vladislav.crm.grpc.DefaultStreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoveLeadEventListener implements ApplicationListener<MoveLeadEvent> {

    private final ReportServiceStub reportService;

    @Override
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
