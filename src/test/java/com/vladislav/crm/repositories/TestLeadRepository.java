package com.vladislav.crm.repositories;

import com.vladislav.crm.IntegrationTestUtils;
import com.vladislav.crm.entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLeadRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private PipelineRepository pipelineRepository;

    @Autowired
    private ContactRepository contactRepository;

    private User user;
    private Lead lead;
    private Pipeline pipeline;
    private Status status;
    private Contact contact;

    @Before
    public void setUp() {
        user = userRepository.save(IntegrationTestUtils.getUser());
        pipeline = pipelineRepository.save(new Pipeline().setName("pipeline").setUser(user));
        status = statusRepository.save(new Status().setName("status").setPipeline(pipeline));
        contact = contactRepository.save(new Contact().setName("contact").setUser(user));
        lead = leadRepository.save(new Lead().setName("lead").setUser(user).setStatus(status).addContact(contact));
    }

    @After
    public void clear() {
        final Pipeline pipeline = pipelineRepository.findById(this.pipeline.getId()).get();
        for (Status status1 : pipeline.getStatuses()) {
            leadRepository.deleteAll(status1.getLeads());
        }
        statusRepository.deleteAll(pipeline.getStatuses());
        userRepository.delete(user);
    }

    @Test
    public void testFindUserIdById() {
        final Long userId = leadRepository.findUserIdById(lead.getId()).get();
        Assertions.assertEquals(user.getId(), userId);
    }

    @Test
    public void testFindStatusIdById() {
        final Long statusId = leadRepository.findStatusIdById(lead.getId()).get();
        Assertions.assertEquals(status.getId(), statusId);
    }

    @Test
    public void testMoveLeadToStatus() {
        final Status status2 = statusRepository.save(new Status().setName("status2").setPipeline(pipeline));
        leadRepository.moveLeadToStatus(lead.getId(), status2.getId());
        final Lead lead1 = leadRepository.findById(this.lead.getId()).get();
        Assertions.assertEquals(status2, lead1.getStatus());
    }

    @Test
    public void testAttachLeadToContact() {
        final Contact contact2 = contactRepository.save(new Contact().setName("contact2").setUser(user));
        leadRepository.attachLeadToContact(lead.getId(), contact2.getId());
        final Lead lead1 = leadRepository.findById(this.lead.getId()).get();
        Assertions.assertEquals(2, lead1.getContacts().size());
        Assertions.assertTrue(lead1.getContacts().contains(contact2));
        Assertions.assertTrue(lead1.getContacts().contains(contact));
    }

    @Test
    public void testFindLeadNameById() {
        final String name = leadRepository.findLeadNameById(lead.getId()).get();
        Assertions.assertEquals(lead.getName(), name);
    }
}
