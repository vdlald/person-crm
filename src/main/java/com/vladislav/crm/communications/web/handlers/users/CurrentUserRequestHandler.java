package com.vladislav.crm.communications.web.handlers.users;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
import org.springframework.hateoas.EntityModel;

public interface CurrentUserRequestHandler extends RequestHandler<Void, EntityModel<GetCurrentUserResponse>> {

    EntityModel<GetCurrentUserResponse> handle();
}
