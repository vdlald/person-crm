package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserAndContactRelationshipsConsistency {

    private User user;
    private Contact contact;

    @BeforeEach
    public void setUp() {
        user = new User();
        contact = new Contact();
    }

    @Test
    public void setUser() {
        contact.setUser(user);
        checkAdd();

        contact.setUser(null);
        checkRemove();
    }

    @Test
    public void addAndRemoveContact() {
        user.addContact(contact);
        checkAdd();

        user.removeContact(contact);
        checkRemove();
    }

    private void checkAdd() {
        assertEquals(user, contact.getUser());
        assertEquals(contact, user.getContacts().get(0));
    }

    private void checkRemove() {
        assertTrue(user.getContacts().isEmpty());
        assertNull(contact.getUser());
    }
}
