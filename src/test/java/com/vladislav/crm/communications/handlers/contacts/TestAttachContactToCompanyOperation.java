package com.vladislav.crm.communications.handlers.contacts;

import com.vladislav.crm.communications.handlers.contacts.impl.AttachContactToCompanyRequestHandlerImpl;
import com.vladislav.crm.services.operations.contacts.AttachContactToCompanyOperation;
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
public class TestAttachContactToCompanyOperation {

    @Mock
    private AttachContactToCompanyOperation attachContactToCompanyOperation;

    @Captor
    private ArgumentCaptor<Long> firstArgumentCaptor;

    @Captor
    private ArgumentCaptor<Long> secondArgumentCaptor;

    private AttachContactToCompanyRequestHandler requestHandler;

    @BeforeEach
    public void setUp() {
        requestHandler = new AttachContactToCompanyRequestHandlerImpl(attachContactToCompanyOperation);
    }

    @Test
    public void testHandle() {
        requestHandler.handle(Pair.of(1L, 2L));

        Mockito.verify(attachContactToCompanyOperation, Mockito.times(1))
                .execute(firstArgumentCaptor.capture(), secondArgumentCaptor.capture());

        final Long contactId = firstArgumentCaptor.getValue();
        final Long companyId = secondArgumentCaptor.getValue();

        Assertions.assertEquals(1L, contactId);
        Assertions.assertEquals(2L, companyId);
    }
}
