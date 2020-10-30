package com.vladislav.crm.communications.handlers.contacts;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.entities.Contact;

import java.util.Collection;

public interface ReadUserContactsRequestHandler extends RequestHandler<Void, Collection<Contact>> {
    Collection<Contact> handle();
}
