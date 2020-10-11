package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCompanyAndContactRelationshipsConsistency {

    private Company company;
    private Contact contact;

    @BeforeEach
    public void setUp() {
        company = new Company();
        company.setId(1L);
        contact = new Contact();
    }

    @Test
    public void setCompany() {
        contact.setCompany(company);
        checkAdd();

        final Company company2 = new Company();
        company2.setId(2L);
        contact.setCompany(company2);
        assertTrue(company.getContacts().isEmpty());
        assertEquals(company2, contact.getCompany());

        contact.setCompany(null);
        assertTrue(company2.getContacts().isEmpty());
        assertNull(contact.getCompany());
    }

    @Test
    public void addAndRemoveCompany() {
        company.addContact(contact);
        checkAdd();

        company.removeContact(contact);
        checkRemove();
    }

    private void checkAdd() {
        assertEquals(company, contact.getCompany());
        assertEquals(contact, company.getContacts().get(0));
    }

    private void checkRemove() {
        assertTrue(company.getContacts().isEmpty());
        assertNull(contact.getCompany());
    }
}
