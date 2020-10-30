package com.vladislav.crm.communications.web.adapters.leads;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadLeadRequestHandlerAdapter extends RequestHandlerAdapter<Long, EntityModel<ReadLeadResponse>> {
}
