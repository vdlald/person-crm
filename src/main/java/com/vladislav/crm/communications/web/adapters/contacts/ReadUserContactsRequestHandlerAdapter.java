package com.vladislav.crm.communications.web.adapters.contacts;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserContactsRequestHandlerAdapter extends RequestHandlerAdapter<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
