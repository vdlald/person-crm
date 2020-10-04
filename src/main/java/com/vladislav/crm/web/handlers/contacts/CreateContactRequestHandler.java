package com.vladislav.crm.web.handlers.contacts;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.CreateContactRequest;
import com.vladislav.crm.web.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateContactRequestHandler extends RequestHandler<CreateContactRequest, EntityModel<ReadContactResponse>> {
}
