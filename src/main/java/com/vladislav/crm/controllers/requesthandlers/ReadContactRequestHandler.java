package com.vladislav.crm.controllers.requesthandlers;

import com.vladislav.crm.controllers.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface ReadContactRequestHandler extends RequestHandler<Long, ResponseEntity<EntityModel<ReadContactResponse>>> {
}
