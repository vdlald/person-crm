package com.vladislav.crm.functions.impl;

import com.vladislav.crm.entities.RefreshToken;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.functions.GenerateRefreshTokenFunction;
import com.vladislav.crm.repositories.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenerateRefreshTokenFunctionImpl implements GenerateRefreshTokenFunction {

    @Value("${app.jwt.refresh-token-lifetime}")
    private Integer refreshTokenLifetime;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken apply(User user) {
        final Instant createdAt = Instant.now();
        final Instant expiredAt = createdAt.plus(refreshTokenLifetime, ChronoUnit.DAYS);

        final RefreshToken refreshToken = new RefreshToken().setUserUnsafe(user)
                .setValidUntil(LocalDateTime.ofInstant(expiredAt, ZoneId.systemDefault()));

        return refreshTokenRepository.save(refreshToken);
    }
}
