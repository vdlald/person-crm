package com.vladislav.crm.communications.handlers.auth.impl;

import com.vladislav.crm.communications.handlers.auth.AuthRequestHandler;
import com.vladislav.crm.communications.requests.AuthRequest;
import com.vladislav.crm.communications.responses.AuthResponse;
import com.vladislav.crm.entities.RefreshToken;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.functions.GenerateRefreshTokenFunction;
import com.vladislav.crm.services.TokenService;
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
    private final GenerateRefreshTokenFunction generateRefreshTokenFunction;

    @Override
    public AuthResponse handle(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        final User user = (User) userDetailsService.loadUserByUsername(authRequest.getUsername());

        final String accessToken = tokenService.generateAccessToken(user);
        final RefreshToken refreshToken = generateRefreshTokenFunction.apply(user);

        return new AuthResponse()
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken);
    }
}
