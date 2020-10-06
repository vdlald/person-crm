package com.vladislav.crm.web.handlers.users;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.responses.GetCurrentUserResponse;
import org.springframework.hateoas.EntityModel;

public interface CurrentUserRequestHandler extends RequestHandler<Void, EntityModel<GetCurrentUserResponse>> {

    EntityModel<GetCurrentUserResponse> handle();
}
