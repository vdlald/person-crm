package com.vladislav.crm.communications.handlers.contacts;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import com.vladislav.crm.entities.Contact;
import org.springframework.hateoas.EntityModel;

public interface ReadContactRequestHandler extends RequestHandler<Long, Contact> {
}
