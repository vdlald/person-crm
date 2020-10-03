package com.vladislav.crm.web.requesthandlers;

public interface RequestHandler<REQ, RES> {
    RES handle(REQ req);
}
