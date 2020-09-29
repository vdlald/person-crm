package com.vladislav.crm.controllers.advices;

import com.vladislav.crm.controllers.responses.ErrorResponse;
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
        final ConstraintViolationException cause = (ConstraintViolationException) exception.getRootCause();
        assert cause != null;

        final String message = cause.getConstraintViolations().stream()
                .map(constraintViolation -> String.format("%s: %s", constraintViolation.getPropertyPath(),
                        constraintViolation.getMessage()))
                .collect(Collectors.joining("\n"));

        return ErrorResponse.builder()
                .setStatus(HttpStatus.BAD_REQUEST)
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
