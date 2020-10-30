package com.vladislav.crm.communications.web.handlers.auth.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.TokenService;
import com.vladislav.crm.communications.web.handlers.auth.AuthRequestHandler;
import com.vladislav.crm.communications.web.requests.AuthRequest;
import com.vladislav.crm.communications.web.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthRequestHandlerImpl implements AuthRequestHandler {

    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse handle(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final User user = (User) userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String token = tokenService.generateToken(user);
        return new AuthResponse().setToken(token);
    }
}
