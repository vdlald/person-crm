package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.leads.impl.GetAllLeadsInExcelRequestHandlerImpl;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.functions.CreateExcelFromLeadsFunction;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.services.operations.users.ReadUserLeadsOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestGetAllLeadsInExcelRequestHandler {

    @Mock
    private GetCurrentUserOperation getCurrentUserStubOperation;

    @Mock
    private ReadUserLeadsOperation readUserLeadsOperation;

    @Mock
    private CreateExcelFromLeadsFunction createExcelFromLeadsFunction;

    private GetAllLeadsInExcelRequestHandler requestHandler;

    private User user;
    private List<Lead> leads;
    private HSSFWorkbook workbook;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);
        Mockito.when(getCurrentUserStubOperation.execute())
                .thenReturn(user);

        leads = List.of(TestUtils.getLead(2L));
        Mockito.when(readUserLeadsOperation.execute(user.getId()))
                .thenReturn(leads);

        workbook = new HSSFWorkbook();
        Mockito.when(createExcelFromLeadsFunction.apply(leads))
                .thenReturn(Pair.of(workbook, workbook.createSheet()));

        requestHandler = new GetAllLeadsInExcelRequestHandlerImpl(
                getCurrentUserStubOperation, readUserLeadsOperation, createExcelFromLeadsFunction);
    }

    @Test
    public void test() {
        final Workbook handle = requestHandler.handle();

        Mockito.verify(getCurrentUserStubOperation, Mockito.times(1))
                .execute();

        Mockito.verify(readUserLeadsOperation, Mockito.times(1))
                .execute(user.getId());

        Mockito.verify(createExcelFromLeadsFunction, Mockito.times(1))
                .apply(leads);

        Assertions.assertEquals(workbook, handle);
    }
}
