package com.vladislav.crm.events.listeners;

import com.proto.report.AddNewLeadLogRequest;
import com.proto.report.ReportServiceGrpc.ReportServiceStub;
import com.vladislav.crm.events.CreateLeadEvent;
import com.vladislav.crm.communications.grpc.DefaultStreamObserver;
import com.vladislav.crm.services.operations.leads.GetUserIdByLeadIdOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateLeadEventListener {

    private final ReportServiceStub reportService;
    private final GetUserIdByLeadIdOperation getUserIdByLeadIdOperation;

    @Async
    @EventListener
    public void onApplicationEvent(CreateLeadEvent event) {
        final Long leadId = event.getSource().getId();

        final AddNewLeadLogRequest request = AddNewLeadLogRequest.newBuilder()
                .setLeadId(leadId)
                .setUserId(getUserIdByLeadIdOperation.execute(leadId))
                .build();

        reportService.addNewLeadLog(request, new DefaultStreamObserver<>());
    }
}
