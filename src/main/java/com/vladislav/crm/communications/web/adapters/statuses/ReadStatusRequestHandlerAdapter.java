package com.vladislav.crm.communications.web.adapters.statuses;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadStatusRequestHandlerAdapter extends RequestHandlerAdapter<Long, EntityModel<ReadStatusResponse>> {
}
