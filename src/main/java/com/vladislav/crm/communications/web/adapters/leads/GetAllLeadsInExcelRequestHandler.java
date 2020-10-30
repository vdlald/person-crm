package com.vladislav.crm.communications.web.adapters.leads;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.data.util.Pair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GetAllLeadsInExcelRequestHandler extends RequestHandlerAdapter<Pair<HttpServletRequest, HttpServletResponse>, Void> {
}
