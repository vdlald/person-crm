package com.vladislav.crm.communications.handlers.auth.impl;

import com.vladislav.crm.communications.handlers.auth.RefreshAccessTokenRequestHandler;
import com.vladislav.crm.communications.responses.AuthResponse;
import com.vladislav.crm.entities.RefreshToken;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.functions.GenerateRefreshTokenFunction;
import com.vladislav.crm.services.TokenService;
import com.vladislav.crm.services.operations.refreshtokens.DeleteRefreshTokenOperation;
import com.vladislav.crm.services.operations.refreshtokens.ReadRefreshTokenOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RefreshAccessTokenRequestHandlerImpl implements RefreshAccessTokenRequestHandler {

    private final TokenService tokenService;
    private final ReadRefreshTokenOperation readRefreshTokenOperation;
    private final DeleteRefreshTokenOperation deleteRefreshTokenOperation;
    private final GenerateRefreshTokenFunction generateRefreshTokenFunction;

    @Override
    public AuthResponse handle(UUID refreshTokenRaw) {
        final RefreshToken refreshToken;
        try {
            refreshToken = readRefreshTokenOperation.execute(refreshTokenRaw);
        } catch (EntityNotFoundException e) {
            throw new AccessDeniedException("Refresh token not found");
        }

        deleteRefreshTokenOperation.execute(refreshToken);

        final LocalDateTime now = LocalDateTime.now();
        if (refreshToken.getValidUntil().isAfter(now)) {
            final User user = refreshToken.getUser();

            return new AuthResponse()
                    .setAccessToken(tokenService.generateAccessToken(user))
                    .setRefreshToken(generateRefreshTokenFunction.apply(user));
        } else {
            throw new AccessDeniedException("Refresh token has expired");
        }
    }
}
