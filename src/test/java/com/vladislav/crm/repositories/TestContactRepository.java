package com.vladislav.crm.repositories;

import com.vladislav.crm.IntegrationTestUtils;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestContactRepository {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Company company;
    private Contact contact;

    @Before
    public void setUp() {
        user = userRepository.save(IntegrationTestUtils.getUser());
        company = companyRepository.save(new Company().setUser(user).setName("company"));
        contact = contactRepository.save(new Contact().setName("contact").setUser(user).setCompany(company));
    }

    @After
    public void clear() {
        userRepository.delete(user);
    }

    @Test
    public void testFindAllByUserId() {
        final Collection<Contact> contacts = contactRepository.findAllByUserId(user.getId());
        Assertions.assertEquals(contact, contacts.iterator().next());
    }

    @Test
    public void testFindUserIdById() {
        final Long userId = contactRepository.findUserIdById(contact.getId()).get();
        Assertions.assertEquals(user.getId(), userId);
    }

    @Test
    public void testAttachContactToCompany() {
        final Company company2 = companyRepository.save(new Company().setName("company2").setUser(user));
        contactRepository.attachContactToCompany(contact.getId(), company2.getId());

        final Contact contact1 = contactRepository.findById(this.contact.getId()).get();
        Assertions.assertEquals(company2.getId(), contact1.getCompany().getId());
    }
}
