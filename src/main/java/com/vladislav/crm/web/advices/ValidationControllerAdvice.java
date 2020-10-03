package com.vladislav.crm.web.advices;

import com.vladislav.crm.web.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionSystemException.class)
    public ErrorResponse constraintViolationHandler(TransactionSystemException exception) {
        final String message;
        final HttpStatus status;

        final Throwable rootCause = exception.getRootCause();
        if (rootCause instanceof ConstraintViolationException) {
            final ConstraintViolationException cause = (ConstraintViolationException) rootCause;

            message = cause.getConstraintViolations().stream()
                    .map(constraintViolation -> String.format("%s: %s",
                            constraintViolation.getPropertyPath(), constraintViolation.getMessage()))
                    .collect(Collectors.joining("\n"));

            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = exception.getMessage();
        }
        
        return ErrorResponse.builder()
                .setStatus(status)
                .setMessage(message)
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse validationError(MethodArgumentNotValidException e) {
        final String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> {
                    final String field = fieldError.getField();
                    final String defaultMessage = fieldError.getDefaultMessage();
                    return String.format("%s: %s", field, defaultMessage);
                })
                .collect(Collectors.joining("; "));

        return ErrorResponse.builder()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setMessage(message)
                .build();
    }
}
