package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserAndLeadRelationshipsConsistency {

    private User user;
    private Lead lead;

    @BeforeEach
    public void setUp() {
        user = new User();
        lead = new Lead();
    }

    @Test
    public void setUser() {
        lead.setUser(user);
        checkAdd();

        lead.setUser(null);
        checkRemove();
    }

    @Test
    public void addAndRemoveLead() {
        user.addLead(lead);
        checkAdd();

        user.removeLead(lead);
        checkRemove();
    }

    private void checkAdd() {
        assertEquals(user, lead.getUser());
        assertEquals(lead, user.getLeads().get(0));
    }

    private void checkRemove() {
        assertTrue(user.getLeads().isEmpty());
        assertNull(lead.getUser());
    }
}
