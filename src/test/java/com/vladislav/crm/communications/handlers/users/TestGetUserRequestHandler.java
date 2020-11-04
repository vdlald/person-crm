package com.vladislav.crm.communications.handlers.users;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.users.impl.GetUserRequestHandlerImpl;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.ReadOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestGetUserRequestHandler {

    @Mock
    private ReadOperation<User> userReadOperation;

    private GetUserRequestHandler requestHandler;
    private User user;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);
        Mockito.when(userReadOperation.execute(user.getId())).thenReturn(user);

        requestHandler = new GetUserRequestHandlerImpl(userReadOperation);
    }

    @Test
    public void testHandle() {
        final User handle = requestHandler.handle(user.getId());
        Assertions.assertEquals(user, handle);
        Mockito.verify(userReadOperation).execute(user.getId());
    }
}
