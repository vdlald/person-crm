package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.communications.handlers.leads.impl.AttachLeadToContactRequestHandlerImpl;
import com.vladislav.crm.services.operations.leads.AttachLeadToContactOperation;
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
public class TestAttachLeadToContactRequestHandler {

    @Mock
    private AttachLeadToContactOperation attachLeadToContactOperation;

    @Captor
    private ArgumentCaptor<Long> leadIdCaptor;

    @Captor
    private ArgumentCaptor<Long> contactIdCaptor;

    private AttachLeadToContactRequestHandler requestHandler;

    @BeforeEach
    public void setUp() {
        requestHandler = new AttachLeadToContactRequestHandlerImpl(attachLeadToContactOperation);
    }

    @Test
    public void test() {
        final long expectedLeadId = 1L;
        final long expectedContactId = 2L;
        requestHandler.handle(Pair.of(expectedLeadId, expectedContactId));

        Mockito.verify(attachLeadToContactOperation, Mockito.times(1))
                .execute(leadIdCaptor.capture(), contactIdCaptor.capture());

        final Long leadId = leadIdCaptor.getValue();
        Assertions.assertEquals(expectedLeadId, leadId);

        final Long contactId = contactIdCaptor.getValue();
        Assertions.assertEquals(expectedContactId, contactId);
    }
}
