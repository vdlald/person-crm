package com.vladislav.crm.communications.web.advices;

import com.vladislav.crm.AppUtils;
import com.vladislav.crm.communications.web.responses.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception e) {
        log.error(e.getLocalizedMessage(), e);
        return ErrorResponse.builder()
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setMessage(AppUtils.getMessage(e))
                .build();
    }
}
