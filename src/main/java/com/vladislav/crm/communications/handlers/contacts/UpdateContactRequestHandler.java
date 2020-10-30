package com.vladislav.crm.communications.handlers.contacts;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.UpdateContactRequest;
import com.vladislav.crm.entities.Contact;
import org.springframework.data.util.Pair;

public interface UpdateContactRequestHandler extends RequestHandler<Pair<Long, UpdateContactRequest>, Contact> {
}
