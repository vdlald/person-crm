package com.vladislav.crm.repositories;

import com.vladislav.crm.IntegrationTestUtils;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPipelineRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PipelineRepository pipelineRepository;

    private User user;
    private Pipeline pipeline;

    @Before
    public void setUp() {
        user = userRepository.save(IntegrationTestUtils.getUser());
        pipeline = pipelineRepository.save(new Pipeline().setName("pipeline").setUser(user));
    }

    @After
    public void clear() {
        userRepository.delete(user);
    }

    @Test
    public void testFindUserIdById() {
        final Long userId = pipelineRepository.findUserIdById(pipeline.getId()).get();
        Assertions.assertEquals(user.getId(), userId);
    }
}
