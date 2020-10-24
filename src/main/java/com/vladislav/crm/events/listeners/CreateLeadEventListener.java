package com.vladislav.crm.events.listeners;

import com.proto.report.AddNewLeadLogRequest;
import com.proto.report.ReportServiceGrpc.ReportServiceStub;
import com.vladislav.crm.events.CreateLeadEvent;
import com.vladislav.crm.grpc.DefaultStreamObserver;
import com.vladislav.crm.services.operations.leads.GetUserIdByLeadIdOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateLeadEventListener implements ApplicationListener<CreateLeadEvent> {

    private final ReportServiceStub reportService;
    private final GetUserIdByLeadIdOperation getUserIdByLeadIdOperation;

    @Override
    public void onApplicationEvent(CreateLeadEvent event) {
        final Long leadId = event.getSource().getId();

        final AddNewLeadLogRequest request = AddNewLeadLogRequest.newBuilder()
                .setLeadId(leadId)
                .setUserId(getUserIdByLeadIdOperation.execute(leadId))
                .build();

        reportService.addNewLeadLog(request, new DefaultStreamObserver<>());
    }
}
