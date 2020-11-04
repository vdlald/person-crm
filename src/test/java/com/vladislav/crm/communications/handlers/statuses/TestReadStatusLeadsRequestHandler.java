package com.vladislav.crm.communications.handlers.statuses;

import com.vladislav.crm.communications.handlers.statuses.impl.ReadStatusLeadsRequestHandlerImpl;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.leads.ReadStatusLeadsOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class TestReadStatusLeadsRequestHandler {

    @Mock
    private ReadStatusLeadsOperation readStatusLeadsOperation;

    private ReadStatusLeadsRequestHandler requestHandler;
    private long expectedId;

    @BeforeEach
    public void setUp() {
        expectedId = 1L;
        Mockito.when(readStatusLeadsOperation.execute(expectedId)).thenReturn(Collections.emptyList());

        requestHandler = new ReadStatusLeadsRequestHandlerImpl(readStatusLeadsOperation);
    }

    @Test
    public void testHandle() {
        final Collection<Lead> handle = requestHandler.handle(expectedId);
        Assertions.assertEquals(Collections.emptyList(), handle);

        Mockito.verify(readStatusLeadsOperation).execute(expectedId);
    }
}
