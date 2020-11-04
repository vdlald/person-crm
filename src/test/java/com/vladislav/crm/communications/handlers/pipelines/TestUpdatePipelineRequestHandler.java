package com.vladislav.crm.communications.handlers.pipelines;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.pipelines.impl.UpdatePipelineRequestHandlerImpl;
import com.vladislav.crm.communications.requests.UpdatePipelineRequest;
import com.vladislav.crm.entities.Pipeline;
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
public class TestUpdatePipelineRequestHandler {

    @Mock
    private ReadOperation<Pipeline> readPipelineOperation;

    @Mock
    private UpdateOperation<Pipeline> pipelineUpdateOperation;

    @Captor
    private ArgumentCaptor<Pipeline> pipelineCaptor;

    private UpdatePipelineRequestHandler requestHandler;
    private Pipeline pipeline;

    @BeforeEach
    public void setUp() {
        pipeline = TestUtils.getPipeline(1L);

        Mockito.when(readPipelineOperation.execute(pipeline.getId())).thenReturn(pipeline);
        Mockito.when(pipelineUpdateOperation.execute(pipeline)).thenReturn(pipeline);

        requestHandler = new UpdatePipelineRequestHandlerImpl(readPipelineOperation, pipelineUpdateOperation);
    }

    @Test
    public void testHandle() {
        final UpdatePipelineRequest request = new UpdatePipelineRequest().setName("name");
        final Pipeline handle = requestHandler.handle(Pair.of(pipeline.getId(), request));

        Assertions.assertEquals(pipeline, handle);

        Mockito.verify(readPipelineOperation).execute(pipeline.getId());
        Mockito.verify(pipelineUpdateOperation).execute(pipelineCaptor.capture());

        final Pipeline capture = pipelineCaptor.getValue();
        Assertions.assertEquals("name", pipeline.getName());
        Assertions.assertEquals(pipeline.getId(), capture.getId());
    }
}
