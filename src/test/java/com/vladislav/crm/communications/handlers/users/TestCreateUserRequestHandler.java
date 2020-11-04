package com.vladislav.crm.communications.handlers.users;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.users.impl.CreateUserRequestHandlerImpl;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
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
public class TestCreateUserRequestHandler {

    @Mock
    private CreateOperation<User> createUserOperation;

    @Captor
    private ArgumentCaptor<User> argumentCaptor;

    private CreateUserRequestHandler requestHandler;
    private User user;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);
        Mockito.when(createUserOperation.execute(Mockito.any(User.class))).thenReturn(user);

        requestHandler = new CreateUserRequestHandlerImpl(createUserOperation);
    }

    @Test
    public void testHandle() {
        final User handle = requestHandler.handle("user", "pass");
        Assertions.assertEquals(user, handle);

        Mockito.verify(createUserOperation).execute(argumentCaptor.capture());

        final User value = argumentCaptor.getValue();
        Assertions.assertEquals("user", value.getUsername());
        Assertions.assertEquals("pass", value.getPassword());
    }
}
