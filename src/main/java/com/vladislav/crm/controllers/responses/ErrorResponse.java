package com.vladislav.crm.controllers.responses;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "set")
public class ErrorResponse {
    Integer status;
    String message;
}
