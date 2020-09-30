package com.vladislav.crm.controllers.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReadUserContactsResponse {

    private Long contactId;
    private String name;

    @JsonIgnoreProperties("contactsId")
    private CompanyResponse company;

}
