package com.vladislav.crm.communications.web.responses;

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

    private Long id;
    private String name;

    @JsonIgnoreProperties("contactsId")
    private ReadCompanyResponse company;

}
