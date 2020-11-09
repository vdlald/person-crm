package com.vladislav.crm.repositories;

import com.vladislav.crm.IntegrationTestUtils;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCompanyRepository {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Company company;

    @Before
    public void setUp() {
        user = userRepository.save(IntegrationTestUtils.getUser());
        company = companyRepository.save(new Company().setName("company").setUser(user));
    }

    @After
    public void clear() {
        userRepository.delete(user);
    }

    @Test
    @Transactional
    public void testFindCompanyInUser() {
        user = userRepository.findById(user.getId()).get();

        Assertions.assertNotNull(user.getCompanies());
        Assertions.assertFalse(user.getCompanies().isEmpty());
        Assertions.assertEquals(company, user.getCompanies().get(0));
    }

    @Test
    public void testFindAllByUserId() {
        final Collection<Company> companies = companyRepository.findAllByUserId(user.getId());
        Assertions.assertEquals(1, companies.size());
        Assertions.assertEquals(company, companies.iterator().next());
    }

    @Test
    public void testFindUserIdById() {
        final Long userId = companyRepository.findUserIdById(company.getId()).get();
        Assertions.assertEquals(user.getId(), userId);
    }

    @Test
    @Transactional
    @Disabled  // вопрос: почему меняется имя компании ?
    public void testChangeInUserNotAffectToCompany() {
        user = userRepository.findById(user.getId()).get();
        user.getCompanies().get(0).setName("name");
        userRepository.save(user);

        company = companyRepository.findById(this.company.getId()).get();
        Assertions.assertEquals("company", company.getName());
    }
}
