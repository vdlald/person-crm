package com.vladislav.crm.communications.handlers.contacts;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.contacts.impl.UpdateContactRequestHandlerImpl;
import com.vladislav.crm.communications.requests.UpdateContactRequest;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
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
public class TestUpdateContactRequestHandler {

    @Mock
    private ReadOperation<Contact> readContactOperation;

    @Mock
    private UpdateOperation<Contact> updateContactOperation;

    @Captor
    private ArgumentCaptor<Long> contactIdCaptor;

    @Captor
    private ArgumentCaptor<Contact> contactArgumentCaptor;

    private UpdateContactRequestHandler requestHandler;

    @BeforeEach
    public void setUp() {
        Mockito.when(readContactOperation.execute(1L))
                .thenReturn(TestUtils.getContact(1L).setName("contact"));

        requestHandler = new UpdateContactRequestHandlerImpl(readContactOperation, updateContactOperation);
    }

    @Test
    public void testHandle() {
        requestHandler.handle(Pair.of(1L, new UpdateContactRequest().setName("name")));

        Mockito.verify(readContactOperation, Mockito.times(1))
                .execute(contactIdCaptor.capture());

        final Long contactId = contactIdCaptor.getValue();
        Assertions.assertEquals(1L, contactId);

        Mockito.verify(updateContactOperation, Mockito.times(1))
                .execute(contactArgumentCaptor.capture());

        final Contact contact = contactArgumentCaptor.getValue();
        Assertions.assertEquals("name", contact.getName());
    }
}
