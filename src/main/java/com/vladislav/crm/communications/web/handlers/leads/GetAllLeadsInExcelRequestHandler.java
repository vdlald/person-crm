package com.vladislav.crm.communications.web.handlers.leads;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.data.util.Pair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GetAllLeadsInExcelRequestHandler extends RequestHandler<Pair<HttpServletRequest, HttpServletResponse>, Void> {
}
