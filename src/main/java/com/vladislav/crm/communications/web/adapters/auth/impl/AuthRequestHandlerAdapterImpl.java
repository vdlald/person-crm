package com.vladislav.crm.communications.web.adapters.auth.impl;

import com.vladislav.crm.communications.handlers.auth.AuthRequestHandler;
import com.vladislav.crm.communications.requests.AuthRequest;
import com.vladislav.crm.communications.web.adapters.auth.AuthRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("webAuthRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthRequestHandlerAdapterImpl implements AuthRequestHandlerAdapter {

    private final AuthRequestHandler requestHandler;

    @Override
    public AuthResponse handle(AuthRequest authRequest) {
        return requestHandler.handle(authRequest);
    }
}
