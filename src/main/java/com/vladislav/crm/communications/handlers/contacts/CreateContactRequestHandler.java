package com.vladislav.crm.communications.handlers.contacts;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.CreateContactRequest;
import com.vladislav.crm.entities.Contact;

public interface CreateContactRequestHandler extends RequestHandler<CreateContactRequest, Contact> {
}
