package com.vladislav.crm.web.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CompanyResponse {  // todo: разделить на два класса - ReadCompanyResponse, Contact.CompanyResponse

    private Long id;
    private String name;
    private List<Long> contactsId;

}
