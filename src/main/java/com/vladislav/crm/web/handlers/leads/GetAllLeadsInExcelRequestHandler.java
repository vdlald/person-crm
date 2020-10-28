package com.vladislav.crm.web.handlers.leads;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.data.util.Pair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GetAllLeadsInExcelRequestHandler extends RequestHandler<Pair<HttpServletRequest, HttpServletResponse>, Void> {
}
