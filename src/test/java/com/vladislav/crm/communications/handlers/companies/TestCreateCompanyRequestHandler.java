package com.vladislav.crm.communications.handlers.companies;

import com.vladislav.crm.communications.handlers.companies.impl.CreateCompanyRequestHandlerImpl;
import com.vladislav.crm.communications.requests.CreateCompanyRequest;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestCreateCompanyRequestHandler {

    @Mock
    private GetCurrentUserOperation getCurrentUserStubOperation;

    @Mock
    private CreateOperation<Company> companyCreateOperation;

    @Captor
    private ArgumentCaptor<Company> companyArgumentCaptor;

    private CreateCompanyRequestHandler requestHandler;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);

        final Company savedCompany = new Company().setName("company");
        savedCompany.setId(1L);
        savedCompany.setUser(user);

        Mockito.when(getCurrentUserStubOperation.execute()).thenReturn(user);
        Mockito.when(
                companyCreateOperation.execute(Mockito.argThat(c -> c.getName().equals("company")))
        ).thenReturn(savedCompany);

        requestHandler = new CreateCompanyRequestHandlerImpl(getCurrentUserStubOperation, companyCreateOperation);
    }

    @Test
    public void testHandle() {
        requestHandler.handle(new CreateCompanyRequest().setName("company"));

        Mockito.verify(companyCreateOperation, Mockito.times(1))
                .execute(companyArgumentCaptor.capture());

        Mockito.verify(getCurrentUserStubOperation, Mockito.times(1))
                .execute();

        final Company company = companyArgumentCaptor.getValue();

        assertEquals("company", company.getName());
        assertEquals(user, company.getUser());
    }
}
