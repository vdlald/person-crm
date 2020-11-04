package com.vladislav.crm.communications.handlers.statuses;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.statuses.impl.UpdateStatusRequestHandlerImpl;
import com.vladislav.crm.communications.requests.UpdateStatusRequest;
import com.vladislav.crm.entities.Status;
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

@ExtendWith(MockitoExtension.class)
public class TestUpdateStatusRequestHandler {

    @Mock
    private ReadOperation<Status> readStatusOperation;

    @Mock
    private UpdateOperation<Status> statusUpdateOperation;

    @Captor
    private ArgumentCaptor<Status> argumentCaptor;

    private UpdateStatusRequestHandler requestHandler;
    private Status status;

    @BeforeEach
    public void setUp() {
        status = TestUtils.getStatus(1L);
        Mockito.when(readStatusOperation.execute(status.getId())).thenReturn(status);
        Mockito.when(statusUpdateOperation.execute(Mockito.any(Status.class))).thenReturn(status);

        requestHandler = new UpdateStatusRequestHandlerImpl(readStatusOperation, statusUpdateOperation);
    }

    @Test
    public void testHandle() {
        final UpdateStatusRequest request = new UpdateStatusRequest().setName("newname");
        final Status handle = requestHandler.handle(Pair.of(status.getId(), request));

        Assertions.assertEquals(status, handle);
        Mockito.verify(readStatusOperation).execute(status.getId());
        Mockito.verify(statusUpdateOperation).execute(argumentCaptor.capture());

        final Status value = argumentCaptor.getValue();
        Assertions.assertEquals(request.getName(), value.getName());
        Assertions.assertEquals(status.getId(), value.getId());
    }
}
