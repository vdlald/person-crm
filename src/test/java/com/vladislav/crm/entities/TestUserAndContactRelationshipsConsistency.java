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
        user.setId(1L);
        contact = new Contact();
    }

    @Test
    public void setUser() {
        contact.setUserSafe(user);
        checkAdd();

        final User user2 = new User();
        user2.setId(2L);
        contact.setUserSafe(user2);
        assertTrue(user.getContacts().isEmpty());
        assertEquals(contact, user2.getContacts().get(0));

        contact.setUserSafe(null);
        assertTrue(user2.getContacts().isEmpty());
        assertNull(contact.getUser());
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
