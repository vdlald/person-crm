package com.vladislav.crm;

import com.vladislav.crm.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component("jwtFilter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtFilter extends OncePerRequestFilter {

    private final JwtParser jwtParser;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        if (Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            final String token = requestTokenHeader.substring(7);
            final Jws<Claims> jws = jwtParser.parseClaimsJws(token);
            final Claims claims = jws.getBody();

            final User user = new User();
            user.setId(claims.get("userId", Long.class));
            user.setUsername(claims.get("username", String.class));
            user.setAuthorities((List<User.Authority>) claims.get("authorities"));

            final UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
