package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestStatusAndLeadRelationshipsConsistency {

    private Status status;
    private Lead lead;

    @BeforeEach
    public void setUp() {
        status = new Status();
        lead = new Lead();
    }

    @Test
    public void setUser() {
        lead.setStatus(status);
        checkAdd();

        lead.setStatus(null);
        checkRemove();
    }

    @Test
    public void addAndRemoveLead() {
        status.addLead(lead);
        checkAdd();

        status.removeLead(lead);
        checkRemove();
    }

    private void checkAdd() {
        assertEquals(status, lead.getStatus());
        assertEquals(lead, status.getLeads().get(0));
    }

    private void checkRemove() {
        assertTrue(status.getLeads().isEmpty());
        assertNull(lead.getStatus());
    }
}
