package com.vladislav.crm.communications.handlers.users;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.users.impl.UpdateCurrentUserInfoRequestHandlerImpl;
import com.vladislav.crm.communications.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.services.operations.UpdateOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestUpdateCurrentUserInfoRequestHandler {

    @Mock
    private GetCurrentUserOperation getCurrentUserOperation;

    @Mock
    private UpdateOperation<User> userUpdateOperation;

    private UpdateCurrentUserInfoRequestHandler requestHandler;
    private User user;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L).setInfoSafe(new UserInfo().setEmail("").setFirstname("").setLastname(""));

        Mockito.when(getCurrentUserOperation.execute()).thenReturn(user);
        Mockito.when(userUpdateOperation.execute(Mockito.any(User.class))).thenReturn(user);

        requestHandler = new UpdateCurrentUserInfoRequestHandlerImpl(getCurrentUserOperation, userUpdateOperation);
    }

    @Test
    public void testHandle() {
        final UpdateCurrentUserInfoRequest request = new UpdateCurrentUserInfoRequest().
                setEmail("e").setFirstname("f").setLastname("l");

        final User handle = requestHandler.handle(request);
        Assertions.assertEquals(user, handle);

        Mockito.verify(getCurrentUserOperation).execute();
        Mockito.verify(userUpdateOperation).execute(user);

        final UserInfo info = user.getInfo();
        Assertions.assertEquals(request.getEmail(), info.getEmail());
        Assertions.assertEquals(request.getFirstname(), info.getFirstname());
        Assertions.assertEquals(request.getLastname(), info.getLastname());
    }
}
