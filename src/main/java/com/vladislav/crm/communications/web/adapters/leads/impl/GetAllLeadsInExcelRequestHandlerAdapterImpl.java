package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladislav.crm.communications.handlers.leads.GetAllLeadsInExcelRequestHandler;
import com.vladislav.crm.communications.web.adapters.leads.GetAllLeadsInExcelRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.FileResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetAllLeadsInExcelRequestHandlerAdapterImpl implements GetAllLeadsInExcelRequestHandlerAdapter {

    private final ObjectMapper objectMapper;
    private final GetAllLeadsInExcelRequestHandler getAllLeadsInExcelRequestHandler;

    private static final String filename = "leads.xls";

    @Override
    public Void handle(Pair<HttpServletRequest, HttpServletResponse> pair) {
        final HttpServletRequest request = pair.getFirst();
        final HttpServletResponse response = pair.getSecond();

        final Workbook workbook = getAllLeadsInExcelRequestHandler.handle();

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
