package com.vladislav.crm.communications.web.filters;

import com.vladislav.crm.functions.AuthenticateByJwtFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("jwtFilter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtFilter extends OncePerRequestFilter {

    private final AuthenticateByJwtFunction authenticateByJwtFunction;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        authenticateByJwtFunction.apply(requestTokenHeader);
        filterChain.doFilter(request, response);
    }
}
