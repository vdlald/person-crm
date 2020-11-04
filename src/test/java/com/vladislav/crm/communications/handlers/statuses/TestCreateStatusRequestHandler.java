package com.vladislav.crm.communications.handlers.statuses;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.statuses.impl.CreateStatusRequestHandlerImpl;
import com.vladislav.crm.communications.requests.CreateStatusRequest;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestCreateStatusRequestHandler {

    @Mock
    private CreateOperation<Status> statusCreateOperation;

    @Mock
    private ReadOperation<Pipeline> readPipelineOperation;

    @Captor
    private ArgumentCaptor<Status> argumentCaptor;

    private CreateStatusRequestHandler requestHandler;
    private Pipeline pipeline;
    private Status status;

    @BeforeEach
    public void setUp() {
        pipeline = TestUtils.getPipeline(1L);
        Mockito.when(readPipelineOperation.execute(pipeline.getId())).thenReturn(pipeline);

        status = TestUtils.getStatus(2L);
        Mockito.when(statusCreateOperation.execute(Mockito.any(Status.class))).thenReturn(status);

        requestHandler = new CreateStatusRequestHandlerImpl(statusCreateOperation, readPipelineOperation);
    }

    @Test
    public void testHandle() {
        final CreateStatusRequest request = new CreateStatusRequest().setName("name").setPipelineId(1L);
        final Status handle = requestHandler.handle(request);
        Assertions.assertEquals(status, handle);

        Mockito.verify(readPipelineOperation).execute(pipeline.getId());
        Mockito.verify(statusCreateOperation).execute(argumentCaptor.capture());

        final Status value = argumentCaptor.getValue();
        Assertions.assertEquals(request.getName(), value.getName());
        Assertions.assertNotNull(value.getPipeline());
        Assertions.assertEquals(request.getPipelineId(), value.getPipeline().getId());
    }
}
