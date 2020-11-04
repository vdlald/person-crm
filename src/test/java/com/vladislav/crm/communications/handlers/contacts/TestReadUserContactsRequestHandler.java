package com.vladislav.crm.communications.handlers.contacts;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.contacts.impl.ReadUserContactsRequestHandlerImpl;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.contacts.ReadUserContactsOperation;
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
public class TestReadUserContactsRequestHandler {

    @Mock
    private GetCurrentUserOperation getCurrentUserStubOperation;

    @Mock
    private ReadUserContactsOperation readUserContactsOperation;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;

    private ReadUserContactsRequestHandler requestHandler;

    private User user;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);
        Mockito.when(getCurrentUserStubOperation.execute())
                .thenReturn(user);

        requestHandler = new ReadUserContactsRequestHandlerImpl(getCurrentUserStubOperation, readUserContactsOperation);
    }

    @Test
    public void testHandle() {
        requestHandler.handle();

        Mockito.verify(getCurrentUserStubOperation, Mockito.times(1))
                .execute();

        Mockito.verify(readUserContactsOperation, Mockito.times(1))
                .execute(argumentCaptor.capture());

        final Long userId = argumentCaptor.getValue();

        Assertions.assertEquals(user.getId(), userId);
    }
}
