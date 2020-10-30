package com.vladislav.crm.communications.web.advices;

import com.vladislav.crm.communications.web.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccessDeniedControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse accessDeniedException(AccessDeniedException e) {
        return ErrorResponse.builder()
                .setStatus(HttpStatus.UNAUTHORIZED)
                .setMessage(e.getMessage())
                .build();
    }
}
