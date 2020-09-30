package com.vladislav.crm.controllers.requesthandlers;

import com.vladislav.crm.controllers.requests.CreateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateContactRequestHandler extends RequestHandler<CreateContactRequest, EntityModel<ReadContactResponse>> {
    @Override
    EntityModel<ReadContactResponse> handle(CreateContactRequest request);
}
