package com.vladislav.crm.events.listeners;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.events.CreateLeadEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateLeadEventLogger implements ApplicationListener<CreateLeadEvent> {

    @Override
    public void onApplicationEvent(CreateLeadEvent event) {
        final Lead lead = event.getSource();
        log.info("Received new Lead: {}", lead);
    }
}
