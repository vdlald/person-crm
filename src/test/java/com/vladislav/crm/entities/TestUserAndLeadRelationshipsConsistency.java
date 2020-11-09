package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserAndLeadRelationshipsConsistency {

    private User user;
    private Lead lead;

    @BeforeEach
    public void setUp() {
        lead = new Lead();
        user = new User();
        user.setId(1L);
    }

    @Test
    public void setUser() {
        lead.setUserSafe(user);
        checkAdd();

        final User user2 = new User();
        user2.setId(2L);
        lead.setUserSafe(user2);
        assertTrue(user.getLeads().isEmpty());
        assertEquals(lead, user2.getLeads().get(0));

        lead.setUserSafe(null);
        assertTrue(user2.getLeads().isEmpty());
        assertNull(lead.getUser());
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
