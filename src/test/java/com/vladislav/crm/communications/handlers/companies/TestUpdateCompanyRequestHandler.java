package com.vladislav.crm.communications.handlers.companies;

import com.vladislav.crm.communications.handlers.companies.impl.UpdateCompanyRequestHandlerImpl;
import com.vladislav.crm.communications.requests.UpdateCompanyRequest;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.ReadOperation;
import com.vladislav.crm.services.operations.UpdateOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestUpdateCompanyRequestHandler {

    @Mock
    private ReadOperation<Company> readCompanyOperation;

    @Mock
    private UpdateOperation<Company> companyUpdateOperation;

    @Captor
    private ArgumentCaptor<Company> companyArgumentCaptor;

    private UpdateCompanyRequestHandler requestHandler;

    @BeforeEach
    public void setUp() {
        final Company company = new Company().setName("company");

        Mockito.when(readCompanyOperation.execute(1L)).thenReturn(company);

        requestHandler = new UpdateCompanyRequestHandlerImpl(readCompanyOperation, companyUpdateOperation);
    }

    @Test
    public void testHandle() {
        requestHandler.handle(Pair.of(1L, new UpdateCompanyRequest().setName("name")));

        Mockito.verify(readCompanyOperation, Mockito.times(1))
                .execute(1L);

        Mockito.verify(companyUpdateOperation, Mockito.times(1))
                .execute(companyArgumentCaptor.capture());

        final Company company = companyArgumentCaptor.getValue();
        assertEquals("name", company.getName());
    }
}
