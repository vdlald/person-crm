package com.vladislav.crm.web.handlers.leads;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.functions.CreateExcelFromLeadsFunction;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.services.operations.users.ReadUserLeadsOperation;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetAllLeadsInExcelRequestHandlerImpl implements GetAllLeadsInExcelRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final ReadUserLeadsOperation readUserLeadsOperation;

    @Override
    public Void handle(HttpServletResponse response) {
        final User user = getCurrentUserStubOperation.execute();
        final Collection<Lead> leads = readUserLeadsOperation.execute(user.getId());

        final Pair<HSSFWorkbook, HSSFSheet> sheetPair = new CreateExcelFromLeadsFunction().apply(leads);
        final HSSFWorkbook workbook = sheetPair.getFirst();

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=leads.xls");
        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
