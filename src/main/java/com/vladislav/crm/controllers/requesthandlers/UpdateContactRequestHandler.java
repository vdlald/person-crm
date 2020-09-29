package com.vladislav.crm.controllers.requesthandlers;

import com.vladislav.crm.controllers.requests.UpdateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface UpdateContactRequestHandler extends RequestHandler<Pair<Long, UpdateContactRequest>, ResponseEntity<EntityModel<ReadContactResponse>>> {
}