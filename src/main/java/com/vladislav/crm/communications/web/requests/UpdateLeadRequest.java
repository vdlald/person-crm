package com.vladislav.crm.communications.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateLeadRequest {

    @NotBlank
    @Size(min = 1, max = 32)
    private String name;

    @DecimalMin("0.0")
    private BigDecimal sale;

}
