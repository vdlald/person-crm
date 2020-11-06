package com.vladislav.crm.communications.web.adapters.auth.impl;

import com.vladislav.crm.communications.handlers.auth.RefreshAccessTokenRequestHandler;
import com.vladislav.crm.communications.web.adapters.auth.RefreshAccessTokenRequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("webRefreshAccessTokenRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RefreshAccessTokenRequestHandlerAdapterImpl implements RefreshAccessTokenRequestHandlerAdapter {

    private final RefreshAccessTokenRequestHandler requestHandler;

    @Override
    public AuthResponse handle(UUID refreshToken) {
        return AuthResponse.of(requestHandler.handle(refreshToken));
    }
}
