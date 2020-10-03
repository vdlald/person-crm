package com.vladislav.crm.web.handlers.contacts;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.UpdateContactRequest;
import com.vladislav.crm.web.responses.ReadContactResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateContactRequestHandler extends RequestHandler<Pair<Long, UpdateContactRequest>, EntityModel<ReadContactResponse>> {
}