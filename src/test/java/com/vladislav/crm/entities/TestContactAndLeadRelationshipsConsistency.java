package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestContactAndLeadRelationshipsConsistency {

    private Contact contact;
    private Lead lead;

    @BeforeEach
    public void setUp() {
        contact = new Contact();
        lead = new Lead();
    }

    @Test
    public void addAndRemoveContact() {
        lead.addContact(contact);
        checkAdd();

        lead.removeContact(contact);
        checkRemove();
    }

    @Test
    public void addAndRemoveLead() {
        contact.addLead(lead);
        checkAdd();

        contact.removeLead(lead);
        checkRemove();
    }

    private void checkAdd() {
        assertEquals(contact, lead.getContacts().get(0));
        assertEquals(lead, contact.getLeads().get(0));
    }

    private void checkRemove() {
        assertTrue(contact.getLeads().isEmpty());
        assertTrue(lead.getContacts().isEmpty());
    }
}
