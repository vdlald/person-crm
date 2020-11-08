package com.vladislav.crm.functions.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.functions.GenerateAccessTokenFunction;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenerateAccessTokenFunctionImpl implements GenerateAccessTokenFunction {

    private final SecretKey jwtSecretKey;

    @Value("${app.jwt.access-token-lifetime}")
    private Integer accessTokenLifetime;

    @Override
    public String apply(User user) {
        final Instant createdAt = Instant.now();
        final Instant expiredAt = createdAt.plus(accessTokenLifetime, ChronoUnit.MINUTES);

        return Jwts.builder()
                .claim("userId", user.getId())
                .claim("username", user.getUsername())
                .claim("authorities", user.getAuthorities())
                .setIssuedAt(Date.from(createdAt))
                .setExpiration(Date.from(expiredAt))
                .signWith(jwtSecretKey)
                .compact();
    }
}
