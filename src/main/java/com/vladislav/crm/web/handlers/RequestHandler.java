package com.vladislav.crm.web.handlers;

public interface RequestHandler<REQ, RES> {
    RES handle(REQ req);
}
