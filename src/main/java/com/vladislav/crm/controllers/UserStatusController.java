package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;

public interface UserStatusController {
    EntityModel<ReadStatusResponse> readStatus(Long statusId);
}
