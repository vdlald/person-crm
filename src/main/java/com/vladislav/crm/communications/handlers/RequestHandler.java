package com.vladislav.crm.communications.handlers;

public interface RequestHandler<REQ, RES> {
    RES handle(REQ req);
}
