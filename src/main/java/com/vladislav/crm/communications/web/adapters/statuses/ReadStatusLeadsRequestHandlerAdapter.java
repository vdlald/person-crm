package com.vladislav.crm.communications.web.adapters.statuses;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.hateoas.RepresentationModel;

public interface ReadStatusLeadsRequestHandlerAdapter extends RequestHandlerAdapter<Long, RepresentationModel<?>> {
}
