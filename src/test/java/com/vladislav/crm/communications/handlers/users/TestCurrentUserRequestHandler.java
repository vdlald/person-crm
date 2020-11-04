package com.vladislav.crm.communications.handlers.users;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.users.impl.CurrentUserRequestHandlerImpl;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestCurrentUserRequestHandler {

    @Mock
    private GetCurrentUserOperation getCurrentUserOperation;

    private CurrentUserRequestHandler requestHandler;
    private User user;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);
        Mockito.when(getCurrentUserOperation.execute()).thenReturn(user);

        requestHandler = new CurrentUserRequestHandlerImpl(getCurrentUserOperation);
    }

    @Test
    public void testHandle() {
        final User handle = requestHandler.handle();
        Assertions.assertEquals(user, handle);

        Mockito.verify(getCurrentUserOperation).execute();
    }
}
