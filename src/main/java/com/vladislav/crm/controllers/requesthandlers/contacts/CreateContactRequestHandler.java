package com.vladislav.crm.controllers.requesthandlers.contacts;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.controllers.requests.CreateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateContactRequestHandler extends RequestHandler<CreateContactRequest, EntityModel<ReadContactResponse>> {
}
