package com.vladislav.crm.controllers.requesthandlers.contacts;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadContactRequestHandler extends RequestHandler<Long, EntityModel<ReadContactResponse>> {
}
