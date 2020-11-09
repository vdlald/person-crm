package com.vladislav.crm.repositories;

import com.vladislav.crm.IntegrationTestUtils;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestStatusRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PipelineRepository pipelineRepository;

    @Autowired
    private StatusRepository statusRepository;

    private User user;
    private Pipeline pipeline;
    private Status status;

    @Before
    public void setUp() {
        user = userRepository.save(IntegrationTestUtils.getUser());
        pipeline = pipelineRepository.save(new Pipeline().setName("pipeline").setUserSafe(user));

        final String statusName = UUID.randomUUID().toString().substring(0, 10);
        status = statusRepository.save(new Status().setName(statusName).setPipelineSafe(pipeline));
    }

    @After
    public void clear() {
        statusRepository.delete(status);
        pipelineRepository.deleteById(pipeline.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    public void testFindUserIdById() {
        final Long userId = statusRepository.findUserIdById(status.getId()).get();
        Assertions.assertEquals(user.getId(), userId);
    }

    @Test
    public void testFindStatusNameById() {
        final String name = statusRepository.findStatusNameById(status.getId()).get();
        Assertions.assertEquals(status.getName(), name);
    }
}
