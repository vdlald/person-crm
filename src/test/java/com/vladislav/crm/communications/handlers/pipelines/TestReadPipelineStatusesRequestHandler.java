package com.vladislav.crm.communications.handlers.pipelines;

import com.vladislav.crm.communications.handlers.pipelines.impl.ReadPipelineStatusesRequestHandlerImpl;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.services.operations.statuses.ReadPipelineStatusesOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestReadPipelineStatusesRequestHandler {

    @Mock
    private ReadPipelineStatusesOperation readPipelineStatusesOperation;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;

    private ReadPipelineStatusesRequestHandler requestHandler;
    private List<Status> statuses;

    @BeforeEach
    public void setUp() {
        statuses = List.of(new Status().setName("name"));
        Mockito.when(readPipelineStatusesOperation.execute(1L)).thenReturn(statuses);

        requestHandler = new ReadPipelineStatusesRequestHandlerImpl(readPipelineStatusesOperation);
    }

    @Test
    public void testHandle() {
        final Collection<Status> handle = requestHandler.handle(1L);

        Assertions.assertEquals(statuses, handle);

        Mockito.verify(readPipelineStatusesOperation).execute(argumentCaptor.capture());

        final Long pipelineId = argumentCaptor.getValue();
        Assertions.assertEquals(1L, pipelineId);
    }
}
