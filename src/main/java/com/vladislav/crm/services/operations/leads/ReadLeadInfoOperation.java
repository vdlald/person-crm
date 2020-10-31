package com.vladislav.crm.services.operations.leads;

import com.vladislav.crm.entities.Lead;
import lombok.NonNull;

public interface ReadLeadInfoOperation {
    Lead.LeadInfo execute(@NonNull Long id);
}
