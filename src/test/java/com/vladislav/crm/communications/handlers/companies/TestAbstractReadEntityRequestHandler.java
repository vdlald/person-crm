package com.vladislav.crm.communications.handlers.companies;

import com.vladislav.crm.communications.handlers.AbstractReadEntityRequestHandler;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.ReadOperation;
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
public class TestAbstractReadEntityRequestHandler {

    @Mock
    private ReadOperation<User> userReadOperation;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;

    private AbstractReadEntityRequestHandler<User> requestHandler;

    @BeforeEach
    public void setUp() {
        requestHandler = new AbstractReadEntityRequestHandler<>(userReadOperation) {
        };
    }

    @Test
    public void testHandle() {
        final long userId = 1L;
        requestHandler.handle(userId);

        Mockito.verify(userReadOperation, Mockito.times(1))
                .execute(argumentCaptor.capture());

        final Long value = argumentCaptor.getValue();
        Assertions.assertEquals(userId, value);
    }
}
