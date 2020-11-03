package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.communications.handlers.leads.impl.ReadLeadInfoForReportRequestHandlerImpl;
import com.vladislav.crm.communications.requests.ReadLeadInfoForReportRequest;
import com.vladislav.crm.communications.responses.ReadLeadInfoForReportResponse;
import com.vladislav.crm.services.operations.leads.GetLeadNameOperation;
import com.vladislav.crm.services.operations.statuses.GetStatusNameOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestReadLeadInfoForReportRequestHandler {

    @Mock
    private GetLeadNameOperation getLeadNameOperation;

    @Mock
    private GetStatusNameOperation getStatusNameOperation;

    @Captor
    private ArgumentCaptor<Long> leadIdCaptor;

    @Captor
    private ArgumentCaptor<Long> statusIdCaptor;

    private ReadLeadInfoForReportRequestHandler requestHandler;

    @BeforeEach
    public void setUp() {
        Mockito.when(getLeadNameOperation.execute(1L))
                .thenReturn("name");

        Mockito.when(getStatusNameOperation.execute(2L))
                .thenReturn("prev");

        Mockito.when(getStatusNameOperation.execute(3L))
                .thenReturn("next");

        requestHandler = new ReadLeadInfoForReportRequestHandlerImpl(getLeadNameOperation, getStatusNameOperation);
    }

    @Test
    public void testHandle() {
        final ReadLeadInfoForReportRequest request = new ReadLeadInfoForReportRequest()
                .setLeadId(1L)
                .setPrevStatusId(2L)
                .setNextStatusId(3L);

        final ReadLeadInfoForReportResponse response = requestHandler.handle(request);

        Mockito.verify(getLeadNameOperation, Mockito.times(1))
                .execute(leadIdCaptor.capture());

        final Long leadId = leadIdCaptor.getValue();
        Assertions.assertEquals(1L, leadId);

        Mockito.verify(getStatusNameOperation, Mockito.times(2))
                .execute(statusIdCaptor.capture());

        final List<Long> values = statusIdCaptor.getAllValues();

        final Long prevId = values.get(0);
        Assertions.assertEquals(2L, prevId);

        final Long nextId = values.get(1);
        Assertions.assertEquals(3L, nextId);

        Assertions.assertEquals(response.getLeadName(), "name");
        Assertions.assertEquals(response.getPrevStatusName(), "prev");
        Assertions.assertEquals(response.getNextStatusName(), "next");
    }
}
