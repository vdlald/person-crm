package com.vladislav.crm.services.impl;

import com.vladislav.crm.entities.RefreshToken;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.repositories.RefreshTokenRepository;
import com.vladislav.crm.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenServiceImpl implements TokenService {

    private final JwtParser jwtParser;
    private final SecretKey jwtSecretKey;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.jwt.access-token-lifetime}")
    private Integer accessTokenLifetime;

    @Value("${app.jwt.refresh-token-lifetime}")
    private Integer refreshTokenLifetime;

    @Override
    public RefreshToken generateRefreshToken(User user) {
        final Instant createdAt = Instant.now();
        final Instant expiredAt = createdAt.plus(refreshTokenLifetime, ChronoUnit.DAYS);

        final RefreshToken refreshToken = new RefreshToken().setUserUnsafe(user)
                .setValidUntil(LocalDateTime.ofInstant(expiredAt, ZoneId.systemDefault()));

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public String generateAccessToken(User user) {
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
