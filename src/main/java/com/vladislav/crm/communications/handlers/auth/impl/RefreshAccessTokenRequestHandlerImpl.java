package com.vladislav.crm.communications.handlers.auth.impl;

import com.vladislav.crm.communications.handlers.auth.RefreshAccessTokenRequestHandler;
import com.vladislav.crm.communications.responses.AuthResponse;
import com.vladislav.crm.entities.RefreshToken;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.repositories.RefreshTokenRepository;
import com.vladislav.crm.services.TokenService;
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
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public AuthResponse handle(UUID refreshTokenRaw) {
        final RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenRaw)
                .orElseThrow(EntityNotFoundException::new);

        final LocalDateTime now = LocalDateTime.now();
        if (refreshToken.getValidUntil().isBefore(now)) {
            final User user = refreshToken.getUser();

            return new AuthResponse()
                    .setAccessToken(tokenService.generateAccessToken(user))
                    .setRefreshToken(tokenService.generateRefreshToken(user));
        } else {
            throw new AccessDeniedException("Refresh token has expired");
        }
    }
}
