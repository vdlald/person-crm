package com.vladislav.crm.communications.web.handlers.contacts;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.UpdateContactRequest;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateContactRequestHandler extends RequestHandler<Pair<Long, UpdateContactRequest>, EntityModel<ReadContactResponse>> {
}
