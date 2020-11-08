package com.vladislav.crm.communications.web.advices;

import com.vladislav.crm.communications.web.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@ControllerAdvice
public class BadCredentialsExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorResponse badCredentialsHandler(BadCredentialsException e) {
        return ErrorResponse.builder()
                .setStatus(HttpStatus.UNAUTHORIZED)
                .setMessage(Optional.ofNullable(e.getLocalizedMessage())
                        .orElse(Optional.ofNullable(e.getMessage()).orElse("")))
                .build();
    }
}
