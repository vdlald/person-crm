package com.vladislav.crm.communications.web.responses;

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

    private CompanyResponse company;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class CompanyResponse {
        private Long id;
        private String name;
    }
}
