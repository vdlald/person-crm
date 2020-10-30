package com.vladislav.crm.communications.web.handlers;

public interface RequestHandler<REQ, RES> {
    RES handle(REQ req);
}
