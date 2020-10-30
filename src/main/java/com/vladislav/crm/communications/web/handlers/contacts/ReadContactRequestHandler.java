package com.vladislav.crm.communications.web.handlers.contacts;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadContactRequestHandler extends RequestHandler<Long, EntityModel<ReadContactResponse>> {
}
