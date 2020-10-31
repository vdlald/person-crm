package com.vladislav.crm.communications.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReadLeadInfoForReportResponse {

    private String leadName;
    private String prevStatusName;
    private String nextStatusName;

}
