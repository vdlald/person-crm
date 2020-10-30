package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.communications.handlers.RequestHandler;
import org.apache.poi.ss.usermodel.Workbook;

public interface GetAllLeadsInExcelRequestHandler extends RequestHandler<Void, Workbook> {
    Workbook handle();
}
