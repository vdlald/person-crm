package com.vladislav.crm.web.requesthandlers.contacts;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import com.vladislav.crm.web.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadContactRequestHandler extends RequestHandler<Long, EntityModel<ReadContactResponse>> {
}
