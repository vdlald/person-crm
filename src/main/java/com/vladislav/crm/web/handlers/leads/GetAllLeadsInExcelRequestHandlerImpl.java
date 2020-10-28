package com.vladislav.crm.web.handlers.leads;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.functions.CreateExcelFromLeadsFunction;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.services.operations.users.ReadUserLeadsOperation;
import com.vladislav.crm.web.responses.FileResponse;
import lombok.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetAllLeadsInExcelRequestHandlerImpl implements GetAllLeadsInExcelRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final ReadUserLeadsOperation readUserLeadsOperation;
    private final ObjectMapper objectMapper;

    private static final String filename = "leads.xls";

    @Override
    public Void handle(Pair<HttpServletRequest, HttpServletResponse> pair) {
        final HttpServletRequest request = pair.getFirst();
        final HttpServletResponse response = pair.getSecond();

        final User user = getCurrentUserStubOperation.execute();
        final Collection<Lead> leads = readUserLeadsOperation.execute(user.getId());

        final Pair<HSSFWorkbook, HSSFSheet> sheetPair = new CreateExcelFromLeadsFunction().apply(leads);
        final HSSFWorkbook workbook = sheetPair.getFirst();

        final String accept = Optional.ofNullable(request.getHeader("Accept")).orElse("");
        try {
            if (accept.equals(MediaType.APPLICATION_JSON_VALUE)) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                workbook.write(byteArrayOutputStream);
                byteArrayOutputStream.close();

                final FileResponse value = new FileResponse()
                        .setName(filename)
                        .setContent(byteArrayOutputStream.toByteArray());
                objectMapper.writeValue(response.getOutputStream(), value);
            } else {
                response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
                workbook.write(response.getOutputStream());
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
