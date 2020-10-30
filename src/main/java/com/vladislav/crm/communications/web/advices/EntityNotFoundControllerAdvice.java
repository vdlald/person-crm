package com.vladislav.crm.communications.web.advices;

import com.vladislav.crm.communications.web.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@ControllerAdvice
public class EntityNotFoundControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse entityNotFoundHandler(EntityNotFoundException e) {
        return ErrorResponse.builder()
                .setStatus(HttpStatus.NOT_FOUND)
                .setMessage(Optional.ofNullable(e.getMessage()).orElse(""))
                .build();
    }
}
