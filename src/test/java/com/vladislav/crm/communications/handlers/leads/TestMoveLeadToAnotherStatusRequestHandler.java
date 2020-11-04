package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.communications.handlers.leads.impl.MoveLeadToAnotherStatusRequestHandlerImpl;
import com.vladislav.crm.services.operations.leads.MoveLeadToStatusOperation;
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

@ExtendWith(MockitoExtension.class)
public class TestMoveLeadToAnotherStatusRequestHandler {

    @Mock
    private MoveLeadToStatusOperation moveLeadToStatusOperation;

    @Captor
    private ArgumentCaptor<Long> leadIdCaptor;

    @Captor
    private ArgumentCaptor<Long> statusIdCaptor;

    private MoveLeadToAnotherStatusRequestHandler requestHandler;

    @BeforeEach
    public void setUp() {
        requestHandler = new MoveLeadToAnotherStatusRequestHandlerImpl(moveLeadToStatusOperation);
    }

    @Test
    public void testHandle() {
        final long expectedLeadId = 1L;
        final long expectedStatusId = 2L;
        requestHandler.handle(Pair.of(expectedLeadId, expectedStatusId));

        Mockito.verify(moveLeadToStatusOperation, Mockito.times(1))
                .execute(leadIdCaptor.capture(), statusIdCaptor.capture());

        final Long leadId = leadIdCaptor.getValue();
        Assertions.assertEquals(expectedLeadId, leadId);

        final Long statusId = statusIdCaptor.getValue();
        Assertions.assertEquals(expectedStatusId, statusId);
    }
}
