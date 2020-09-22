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
        contact = new Contact();
    }

    @Test
    public void setCompany() {
        contact.setCompany(company);
        checkAdd();

        contact.setCompany(null);
        checkRemove();
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
