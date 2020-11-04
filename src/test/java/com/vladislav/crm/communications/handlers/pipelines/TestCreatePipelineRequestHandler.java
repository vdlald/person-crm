package com.vladislav.crm.communications.handlers.pipelines;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.pipelines.impl.CreatePipelineRequestHandlerImpl;
import com.vladislav.crm.communications.requests.CreatePipelineRequest;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
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

@ExtendWith(MockitoExtension.class)
public class TestCreatePipelineRequestHandler {

    @Mock
    private CreateOperation<Pipeline> createPipelineOperation;

    @Mock
    private GetCurrentUserOperation getCurrentUserStubOperation;

    @Captor
    private ArgumentCaptor<Pipeline> argumentCaptor;

    private CreatePipelineRequestHandler requestHandler;
    private User user;
    private Pipeline pipeline;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);
        Mockito.when(getCurrentUserStubOperation.execute()).thenReturn(user);
        pipeline = TestUtils.getPipeline(2L);
        Mockito.when(createPipelineOperation.execute(Mockito.any(Pipeline.class))).thenReturn(pipeline);

        requestHandler = new CreatePipelineRequestHandlerImpl(createPipelineOperation, getCurrentUserStubOperation);
    }

    @Test
    public void testHandle() {
        final CreatePipelineRequest request = new CreatePipelineRequest().setName("pipeline");
        final Pipeline handle = requestHandler.handle(request);

        Mockito.verify(getCurrentUserStubOperation).execute();
        Mockito.verify(createPipelineOperation).execute(argumentCaptor.capture());

        final Pipeline pipelineCapture = argumentCaptor.getValue();
        Assertions.assertEquals(request.getName(), pipelineCapture.getName());
        Assertions.assertEquals(user, pipelineCapture.getUser());

        Assertions.assertEquals(pipeline, handle);
    }
}
