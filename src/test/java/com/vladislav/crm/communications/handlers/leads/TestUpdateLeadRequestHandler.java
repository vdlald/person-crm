package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.leads.impl.UpdateLeadRequestHandlerImpl;
import com.vladislav.crm.communications.requests.UpdateLeadRequest;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class TestUpdateLeadRequestHandler {

    @Mock
    private ReadOperation<Lead> leadReadOperation;

    @Mock
    private UpdateOperation<Lead> leadUpdateOperation;

    @Captor
    private ArgumentCaptor<Long> leadIdCaptor;

    @Captor
    private ArgumentCaptor<Lead> leadCaptor;

    private UpdateLeadRequestHandler requestHandler;
    private Lead lead;

    @BeforeEach
    public void setUp() {
        lead = TestUtils.getLead(1L);
        Mockito.when(leadReadOperation.execute(lead.getId())).thenReturn(lead);
        Mockito.when(leadUpdateOperation.execute(Mockito.any(Lead.class))).thenReturn(lead);

        requestHandler = new UpdateLeadRequestHandlerImpl(leadReadOperation, leadUpdateOperation);
    }

    @Test
    public void testHandle() {
        final Pair<Long, UpdateLeadRequest> pair = Pair.of(lead.getId(), new UpdateLeadRequest()
                .setName("name").setSale(BigDecimal.ONE));

        final Lead handle = requestHandler.handle(pair);

        Mockito.verify(leadReadOperation, Mockito.times(1))
                .execute(leadIdCaptor.capture());

        final Long leadId = leadIdCaptor.getValue();
        Assertions.assertEquals(lead.getId(), leadId);

        Mockito.verify(leadUpdateOperation, Mockito.times(1))
                .execute(leadCaptor.capture());

        final Lead leadCaptorValue = leadCaptor.getValue();
        Assertions.assertEquals("name", leadCaptorValue.getName());
        Assertions.assertEquals(BigDecimal.ONE, leadCaptorValue.getSale());

        Assertions.assertEquals(lead, handle);
    }
}
