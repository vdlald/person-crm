package com.vladislav.crm.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CreateLeadRequest {

    private String name;
    private BigDecimal sale;
    private Long statusId;
    private List<Long> contactsId;

}