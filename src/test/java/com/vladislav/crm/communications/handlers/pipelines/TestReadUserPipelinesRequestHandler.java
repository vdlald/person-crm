package com.vladislav.crm.communications.handlers.pipelines;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.pipelines.impl.ReadUserPipelinesRequestHandlerImpl;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.pipelines.ReadUserPipelinesOperation;
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

import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestReadUserPipelinesRequestHandler {

    @Mock
    private GetCurrentUserOperation getCurrentUserStubOperation;

    @Mock
    private ReadUserPipelinesOperation readUserPipelinesOperation;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;

    private ReadUserPipelinesRequestHandler requestHandler;
    private User user;
    private List<Pipeline> pipelines;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);
        Mockito.when(getCurrentUserStubOperation.execute()).thenReturn(user);

        pipelines = List.of(TestUtils.getPipeline(2L));
        Mockito.when(readUserPipelinesOperation.execute(user.getId())).thenReturn(pipelines);

        requestHandler = new ReadUserPipelinesRequestHandlerImpl(
                getCurrentUserStubOperation, readUserPipelinesOperation);
    }

    @Test
    public void testHandle() {
        final Collection<Pipeline> handle = requestHandler.handle();
        Assertions.assertEquals(pipelines, handle);

        Mockito.verify(getCurrentUserStubOperation).execute();
        Mockito.verify(readUserPipelinesOperation).execute(argumentCaptor.capture());

        final Long userId = argumentCaptor.getValue();
        Assertions.assertEquals(user.getId(), userId);

    }
}
