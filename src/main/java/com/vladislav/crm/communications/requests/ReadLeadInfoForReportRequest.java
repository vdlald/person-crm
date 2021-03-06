package com.vladislav.crm.communications.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReadLeadInfoForReportRequest {

    private Long leadId;
    private Long prevStatusId;
    private Long nextStatusId;

}
