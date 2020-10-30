package com.vladislav.crm.communications.web.handlers.contacts;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.CreateContactRequest;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateContactRequestHandler extends RequestHandler<CreateContactRequest, EntityModel<ReadContactResponse>> {
}
