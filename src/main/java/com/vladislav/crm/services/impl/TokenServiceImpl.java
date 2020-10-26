package com.vladislav.crm.services.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenServiceImpl implements TokenService {

    private final JwtParser jwtParser;

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

    @Override
    public UserDetails parseToken(String token) {
        final Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        final Claims claims = jws.getBody();

        final User user = new User();
        user.setId(claims.get("userId", Long.class));
        user.setUsername(claims.get("username", String.class));
        user.setAuthorities((List<User.Authority>) claims.get("authorities"));

        return user;
    }
}
