package com.vladislav.crm.communications.handlers;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.DeleteOperation;
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
public class TestAbstractDeleteEntityRequestHandler {

    @Mock
    private DeleteOperation<User> deleteOperation;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;

    private AbstractDeleteEntityRequestHandler<User> requestHandler;

    @BeforeEach
    public void setUp() {
        requestHandler = new AbstractDeleteEntityRequestHandler<>(deleteOperation) {
        };
    }

    @Test
    public void testHandle() {
        final long userId = 1L;
        requestHandler.handle(userId);

        Mockito.verify(deleteOperation, Mockito.times(1)).execute(argumentCaptor.capture());

        final Long value = argumentCaptor.getValue();
        Assertions.assertEquals(userId, value);
    }
}
