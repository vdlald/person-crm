package com.vladislav.crm.controllers.requesthandlers;

public interface RequestHandler<REQ, RES> {
    RES handle(REQ req);
}
