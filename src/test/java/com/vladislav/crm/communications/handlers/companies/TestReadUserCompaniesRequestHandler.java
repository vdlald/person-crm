package com.vladislav.crm.communications.handlers.companies;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.companies.impl.ReadUserCompaniesRequestHandlerImpl;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.companies.ReadUserCompaniesOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestReadUserCompaniesRequestHandler {

    @Mock
    private GetCurrentUserOperation getCurrentUserStubOperation;

    @Mock
    private ReadUserCompaniesOperation readUserCompaniesOperation;

    private ReadUserCompaniesRequestHandler requestHandler;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;

    private User user;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);

        Mockito.when(getCurrentUserStubOperation.execute()).thenReturn(user);

        requestHandler = new ReadUserCompaniesRequestHandlerImpl(getCurrentUserStubOperation, readUserCompaniesOperation);
    }

    @Test
    public void testHandle() {
        requestHandler.handle();

        Mockito.verify(readUserCompaniesOperation, Mockito.times(1))
                .execute(argumentCaptor.capture());

        final Long userId = argumentCaptor.getValue();
        assertEquals(user.getId(), userId);
    }

}
