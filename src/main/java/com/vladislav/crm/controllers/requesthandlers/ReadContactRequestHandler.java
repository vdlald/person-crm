package com.vladislav.crm.controllers.requesthandlers;

import com.vladislav.crm.controllers.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadContactRequestHandler extends RequestHandler<Long, EntityModel<ReadContactResponse>> {
}
