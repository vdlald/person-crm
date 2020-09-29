package com.vladislav.crm.controllers.advices;

import com.vladislav.crm.controllers.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception e) {
        return ErrorResponse.builder()
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(e.getMessage())
                .build();
    }
}