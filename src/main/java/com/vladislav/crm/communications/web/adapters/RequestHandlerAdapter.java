package com.vladislav.crm.communications.web.adapters;

public interface RequestHandlerAdapter<REQ, RES> {
    RES handle(REQ req);
}
