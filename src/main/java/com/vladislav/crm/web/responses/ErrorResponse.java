package com.vladislav.crm.web.responses;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
@Builder(setterPrefix = "set")
public class ErrorResponse {
    HttpStatus status;
    String message;
}