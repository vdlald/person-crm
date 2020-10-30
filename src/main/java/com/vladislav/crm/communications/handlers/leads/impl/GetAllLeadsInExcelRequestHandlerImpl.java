package com.vladislav.crm.communications.handlers.leads.impl;

import com.vladislav.crm.communications.handlers.leads.GetAllLeadsInExcelRequestHandler;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.functions.CreateExcelFromLeadsFunction;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.services.operations.users.ReadUserLeadsOperation;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetAllLeadsInExcelRequestHandlerImpl implements GetAllLeadsInExcelRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final ReadUserLeadsOperation readUserLeadsOperation;

    @Override
    public Workbook handle(Void unused) {
        final User user = getCurrentUserStubOperation.execute();
        final Collection<Lead> leads = readUserLeadsOperation.execute(user.getId());

        final Pair<HSSFWorkbook, HSSFSheet> sheetPair = new CreateExcelFromLeadsFunction().apply(leads);

        return sheetPair.getFirst();
    }

    @Override
    public Workbook handle() {
        return handle(null);
    }
}
