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
public class ReadContactResponse {

    private Long id;
    private String name;
    private Long userId;

    @JsonIgnoreProperties("contactsId")
    private CompanyResponse company;

}
