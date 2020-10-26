package com.vladislav.crm.services.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenServiceImpl implements TokenService {

    @Override
    public String generateToken(User user) {

        final Instant createdAt = Instant.now();
        final Instant expiredAt = createdAt.plus(5, ChronoUnit.MINUTES);  // todo: вынести в application.yaml

        return Jwts.builder()
                .claim("userId", user.getId())
                .claim("username", user.getUsername())
                .claim("authorities", user.getAuthorities())
                .setIssuedAt(Date.from(createdAt))
                .setExpiration(Date.from(expiredAt))
                .signWith(Keys.hmacShaKeyFor("secretsecretsecretsecretsecretsecret".getBytes(StandardCharsets.UTF_8)))  // todo: вынести secret в application.yaml
                .compact();
    }
}
