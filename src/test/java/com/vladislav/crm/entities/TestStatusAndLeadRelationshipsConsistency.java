package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestStatusAndLeadRelationshipsConsistency {

    private Status status;
    private Lead lead;

    @BeforeEach
    public void setUp() {
        status = new Status().setName("status1");
        status.setId(1L);
        lead = new Lead();
    }

    @Test
    public void setStatus() {
        lead.setStatus(status);
        checkAdd(status);

        lead.setStatus(null);
        checkRemove();
    }

    @Test
    public void addAndRemoveLead() {
        status.addLead(lead);
        checkAdd(status);

        status.removeLead(lead);
        checkRemove();
    }

    @Test
    public void moveLead() {
        final Status status2 = new Status().setName("status2");
        status2.setId(2L);

        lead.setStatus(status);
        lead.setStatus(status2);

        checkAdd(status2);
    }

    private void checkAdd(Status status) {
        assertEquals(status, lead.getStatus());
        assertEquals(lead, status.getLeads().get(0));
    }

    private void checkRemove() {
        assertTrue(status.getLeads().isEmpty());
        assertNull(lead.getStatus());
    }
}
