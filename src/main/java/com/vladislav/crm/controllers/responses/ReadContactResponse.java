package com.vladislav.crm.controllers.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladislav.crm.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReadContactResponse {

    private Long id;
    private String name;
    private Long userId;

    @JsonIgnoreProperties("contacts")
    private Company company;

}
