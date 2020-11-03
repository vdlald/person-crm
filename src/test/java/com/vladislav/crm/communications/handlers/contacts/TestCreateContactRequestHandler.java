package com.vladislav.crm.communications.handlers.contacts;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.contacts.impl.CreateContactRequestHandlerImpl;
import com.vladislav.crm.communications.requests.CreateContactRequest;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.ReadOperation;
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
public class TestCreateContactRequestHandler {

    @Mock
    private GetCurrentUserOperation getCurrentUserStubOperation;

    @Mock
    private CreateOperation<Contact> createContactOperation;

    @Mock
    private ReadOperation<Company> readCompanyStubOperation;

    @Captor
    private ArgumentCaptor<Contact> contactArgumentCaptor;

    private CreateContactRequestHandler requestHandler;

    private User user;
    private Company company;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);
        company = TestUtils.getCompany(2L);

        Mockito.when(getCurrentUserStubOperation.execute())
                .thenReturn(user);

        requestHandler = new CreateContactRequestHandlerImpl(
                getCurrentUserStubOperation, createContactOperation, readCompanyStubOperation);
    }

    // companyId is not null
    @Test
    public void testHandleCase1() {
        final long expectedCompanyId = 2L;

        Mockito.when(readCompanyStubOperation.execute(2L))
                .thenReturn(company);

        final ArgumentCaptor<Long> companyIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        requestHandler.handle(new CreateContactRequest().setCompanyId(expectedCompanyId).setName("name"));

        Mockito.verify(readCompanyStubOperation, Mockito.times(1))
                .execute(companyIdArgumentCaptor.capture());

        Mockito.verify(createContactOperation, Mockito.times(1))
                .execute(contactArgumentCaptor.capture());

        final Long companyId = companyIdArgumentCaptor.getValue();
        Assertions.assertEquals(expectedCompanyId, companyId);

        final Contact contact = contactArgumentCaptor.getValue();
        Assertions.assertEquals("name", contact.getName());
        Assertions.assertNotNull(contact.getUser());
        Assertions.assertEquals(user, contact.getUser());
        Assertions.assertNotNull(contact.getCompany());
        Assertions.assertEquals(company, contact.getCompany());
    }

    // companyId is null
    @Test
    public void testHandleCase2() {
        requestHandler.handle(new CreateContactRequest().setName("name"));

        Mockito.verify(readCompanyStubOperation, Mockito.times(0))
                .execute(Mockito.any());

        Mockito.verify(createContactOperation, Mockito.times(1))
                .execute(contactArgumentCaptor.capture());

        final Contact contact = contactArgumentCaptor.getValue();
        Assertions.assertEquals("name", contact.getName());
        Assertions.assertNotNull(contact.getUser());
        Assertions.assertEquals(user, contact.getUser());
        Assertions.assertNull(contact.getCompany());
    }
}
