package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.leads.impl.CreateLeadRequestHandlerImpl;
import com.vladislav.crm.communications.requests.CreateLeadRequest;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class TestCreateLeadRequestHandler {

    @Mock
    private GetCurrentUserOperation getCurrentUserStubOperation;

    @Mock
    private CreateOperation<Lead> leadCreateOperation;

    @Mock
    private ReadOperation<Status> readStatusStubOperation;

    @Captor
    private ArgumentCaptor<Lead> leadArgumentCaptor;

    private User user;
    private Lead lead;

    private CreateLeadRequestHandler requestHandler;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);

        Mockito.when(getCurrentUserStubOperation.execute())
                .thenReturn(user);

        lead = TestUtils.getLead(1L)
                .setName("lead")
                .setSale(BigDecimal.ONE)
                .setStatusUnsafe(TestUtils.getStatus(2L));

        Mockito.when(leadCreateOperation.execute(Mockito.any())).thenReturn(lead);

        requestHandler = new CreateLeadRequestHandlerImpl(
                getCurrentUserStubOperation, leadCreateOperation, readStatusStubOperation);
    }

    // statusId not null
    @Test
    public void testHandleCase1() {
        final ArgumentCaptor<Long> statusIdCaptor = ArgumentCaptor.forClass(Long.class);

        final long expectedStatusId = 2L;
        Mockito.when(readStatusStubOperation.execute(expectedStatusId))
                .thenReturn(TestUtils.getStatus(expectedStatusId));

        final CreateLeadRequest request = new CreateLeadRequest()
                .setName("lead")
                .setSale(BigDecimal.ONE)
                .setStatusId(expectedStatusId);
        final Lead handle = requestHandler.handle(request);

        Mockito.verify(getCurrentUserStubOperation, Mockito.times(1))
                .execute();

        Mockito.verify(readStatusStubOperation, Mockito.times(1))
                .execute(statusIdCaptor.capture());

        final Long statusId = statusIdCaptor.getValue();
        Assertions.assertEquals(expectedStatusId, statusId);

        Mockito.verify(leadCreateOperation, Mockito.times(1))
                .execute(leadArgumentCaptor.capture());

        final Lead leadCapture = leadArgumentCaptor.getValue();
        Assertions.assertEquals("lead", leadCapture.getName());
        Assertions.assertEquals(BigDecimal.ONE, leadCapture.getSale());
        Assertions.assertNotNull(leadCapture.getUser());
        Assertions.assertEquals(user, leadCapture.getUser());
        Assertions.assertNotNull(leadCapture.getStatus());
        Assertions.assertEquals(expectedStatusId, leadCapture.getStatus().getId());

        Assertions.assertEquals(lead, handle);
    }

    // statusId is null
    @Test
    public void testHandleCase2() {
        final CreateLeadRequest request = new CreateLeadRequest()
                .setName("lead")
                .setSale(BigDecimal.ONE);
        final Lead handle = requestHandler.handle(request);

        Mockito.verify(getCurrentUserStubOperation, Mockito.times(1))
                .execute();

        Mockito.verify(readStatusStubOperation, Mockito.times(0))
                .execute(Mockito.any());

        Mockito.verify(leadCreateOperation, Mockito.times(1))
                .execute(leadArgumentCaptor.capture());

        final Lead leadCapture = leadArgumentCaptor.getValue();
        Assertions.assertEquals("lead", leadCapture.getName());
        Assertions.assertEquals(BigDecimal.ONE, leadCapture.getSale());
        Assertions.assertNotNull(leadCapture.getUser());
        Assertions.assertEquals(user, leadCapture.getUser());
        Assertions.assertNull(leadCapture.getStatus());

        Assertions.assertEquals(lead, handle);
    }
}
