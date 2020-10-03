package com.vladislav.crm.web.handlers.contacts;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadContactRequestHandler extends RequestHandler<Long, EntityModel<ReadContactResponse>> {
}
