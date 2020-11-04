package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.communications.handlers.leads.impl.ReadLeadInfoRequestHandlerImpl;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.leads.ReadLeadInfoOperation;
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
public class TestReadLeadInfoRequestHandler {

    @Mock
    private ReadLeadInfoOperation readLeadInfoOperation;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;

    private ReadLeadInfoRequestHandler requestHandler;

    private final long expectedId = 1L;
    private Lead.LeadInfo leadInfo;

    @BeforeEach
    public void setUp() {
        leadInfo = Lead.LeadInfo.builder().setId(expectedId).build();

        Mockito.when(readLeadInfoOperation.execute(expectedId)).thenReturn(leadInfo);

        requestHandler = new ReadLeadInfoRequestHandlerImpl(readLeadInfoOperation);
    }

    @Test
    public void testHandle() {
        final Lead.LeadInfo handle = requestHandler.handle(expectedId);

        Mockito.verify(readLeadInfoOperation).execute(argumentCaptor.capture());

        final Long id = argumentCaptor.getValue();
        Assertions.assertEquals(expectedId, id);

        Assertions.assertEquals(leadInfo, handle);
    }
}
